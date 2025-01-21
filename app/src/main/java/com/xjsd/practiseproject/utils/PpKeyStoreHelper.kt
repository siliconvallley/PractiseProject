package com.xjsd.practiseproject.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.xjsd.practiseproject.ext.logI
import com.xjsd.practiseproject.ext.toByteArray
import com.xjsd.practiseproject.ext.toInt
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.MGF1ParameterSpec
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource

/**
 * 用于对本地数据进行加解密工具类.
 */
object PpKeyStoreHelper {
    private const val TAG = "PpKeyStoreHelper"
    // 密钥库类型
    private const val PP_KEYSTORE_TYPE = "AndroidKeyStore"
    // 密钥库别名（RSA）
    private const val PP_KEYSTORE_RSA_ALIAS = "pp_keystore_rsa_alias"
    // 加密算法标准算法名称; transformation(算法/模式/填充方式): 参数的格式通常为 "algorithm/mode/padding"（RSA）
    private const val PP_RSA_TRANSFORMATION = "RSA/ECB/OAEPwithSHA-512andMGF1Padding"
    // 密钥库别名（AES，用于大数据加密）
    private const val PP_KEYSTORE_AES_ALIAS = "pp_keystore_aes_alias"
    // 加密算法标准算法名称; transformation(算法/模式/填充方式): 参数的格式通常为 "algorithm/mode/padding"（AES）
    private const val PP_AES_TRANSFORMATION = "AES/GCM/NoPadding"
    // AES加密中使用2个字节长度的数组存储IV的长度
    private const val PP_AES_IV_BYTES_LEN = 2

    /**
     * RSA数据加密（数据有大小限制）.
     *
     * @param bytes 原始数据，字节数组
     * @return 加密数据，字节数组
     */
    fun rsaEncryptData(bytes: ByteArray): ByteArray {
        return rsaEncryptDataInternal(bytes)
    }

    /**
     * RSA数据解密（数据有大小限制）.
     *
     * @param bytes 加密数据
     * @return 原始数据，字节数组
     */
    fun rsaDecryptData(bytes: ByteArray): ByteArray {
        return rsaDecryptDataInternal(bytes)
    }

    /**
     * AES数据加密（数据有大小限制）.
     *
     * @param bytes 原始数据，字节数组
     * @return 加密数据，字节数组
     */
    fun aesEncryptData(bytes: ByteArray): ByteArray {
        return try {
            if (bytes.isEmpty()) {
                return byteArrayOf()
            }
            getAesSecretKey()?.let { secretKey ->
                val cipher = Cipher.getInstance(PP_AES_TRANSFORMATION)
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
                val iv = cipher.parameters.getParameterSpec(GCMParameterSpec::class.java).iv
                val ivLenBytes = iv.size.toByteArray()
                // 拼接加密数据：iv长度(2字节) + iv + 真实加密数据
                ivLenBytes.plus(iv).plus(cipher.doFinal(bytes))
            } ?: byteArrayOf()
        } catch (e: Exception) {
            "aesEncryptData error=${e.stackTraceToString()}".logI(TAG)
            byteArrayOf()
        }
    }

    /**
     * AES数据解密（数据有大小限制）.
     *
     * @param bytes 加密数据
     * @return 原始数据，字节数组
     */
    fun aesDecryptData(bytes: ByteArray): ByteArray {
        return try {
            if (bytes.isEmpty() || bytes.size <= PP_AES_IV_BYTES_LEN) {
                return byteArrayOf()
            }
            // 加密数据：iv长度(2字节) + iv + 真实加密数据
            val ivLen = bytes.sliceArray(0 until PP_AES_IV_BYTES_LEN).toInt()
            val inLenEndIndex = PP_AES_IV_BYTES_LEN + ivLen
            val iv = bytes.sliceArray(PP_AES_IV_BYTES_LEN until inLenEndIndex)
            val decryptBytes = bytes.sliceArray(inLenEndIndex until bytes.size)
            return getAesSecretKey()?.let { secretKey ->
                val cipher = Cipher.getInstance(PP_AES_TRANSFORMATION)
                cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
                cipher.doFinal(decryptBytes)
            } ?: byteArrayOf()
        } catch (e: Exception) {
            "aesDecryptData error=${e.stackTraceToString()}".logI(TAG)
            byteArrayOf()
        }
    }

    /**
     * RSA数据加密（数据有大小限制）.
     *
     * @param bytes 原始数据
     * @return 加密数据
     */
    private fun rsaEncryptDataInternal(bytes: ByteArray): ByteArray {
        return getRsaPublicKey()?.run {
            val cipher = Cipher.getInstance(PP_RSA_TRANSFORMATION)
            cipher.init(
                Cipher.ENCRYPT_MODE, this,
                OAEPParameterSpec(
                    "SHA-512",
                    "MGF1",
                    MGF1ParameterSpec.SHA1,
                    PSource.PSpecified.DEFAULT
                )
            )
            cipher.doFinal(bytes)
        } ?: byteArrayOf()
    }

    /**
     * RSA数据解密（数据有大小限制）.
     *
     * @param bytes 加密数据
     * @return 原始数据
     */
    private fun rsaDecryptDataInternal(bytes: ByteArray): ByteArray {
        return getRsaPrivateKey()?.run {
            val cipher = Cipher.getInstance(PP_RSA_TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, this)
            cipher.doFinal(bytes)
        } ?: byteArrayOf()
    }

    /**
     * 获取RSA公钥.
     *
     * @return 公钥
     */
    private fun getRsaPublicKey(): PublicKey? {
        val keyStore = KeyStore.getInstance(PP_KEYSTORE_TYPE).apply {
            load(null)
        }
        // 判断密钥是否存在
        if (!keyStore.containsAlias(PP_KEYSTORE_RSA_ALIAS)) {
            return generateRsaKey().public
        }
        val entry = keyStore.getEntry(PP_KEYSTORE_RSA_ALIAS, null)
        if (entry !is KeyStore.PrivateKeyEntry) {
            return null
        }
        return entry.certificate.publicKey
    }

    /**
     * 获取RSA密钥.
     *
     * @return 密钥
     */
    private fun getRsaPrivateKey(): PrivateKey? {
        val keyStore = KeyStore.getInstance(PP_KEYSTORE_TYPE).apply {
            load(null)
        }
        // 判断密钥是否存在
        if (!keyStore.containsAlias(PP_KEYSTORE_RSA_ALIAS)) {
            return generateRsaKey().private
        }
        val entry = keyStore.getEntry(PP_KEYSTORE_RSA_ALIAS, null)
        if (entry !is KeyStore.PrivateKeyEntry) {
            return null
        }
        return entry.privateKey
    }

    /**
     * 触发生成RSA密钥对.
     *
     * @return KeyPair 密钥对，包含公钥和私钥
     */
    private fun generateRsaKey(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA,
            PP_KEYSTORE_TYPE
        )
        KeyGenParameterSpec.Builder(
            PP_KEYSTORE_RSA_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
            .build().run {
                keyPairGenerator.initialize(this)
            }
        return keyPairGenerator.generateKeyPair()
    }

    /**
     * 从KeyStore获取AES密钥.
     */
    private fun getAesSecretKey(): SecretKey? {
        val keyStore = KeyStore.getInstance(PP_KEYSTORE_TYPE).apply {
            load(null)
        }
        // 判断密钥是否存在
        if (!keyStore.containsAlias(PP_KEYSTORE_AES_ALIAS)) {
            return generateAesSecretKey()
        }
        val entry = keyStore.getEntry(PP_KEYSTORE_AES_ALIAS, null)
        if (entry !is KeyStore.SecretKeyEntry) {
            return null
        }
        return entry.secretKey
    }

    /**
     * 生成对称加密密钥.
     *
     * @return 对称加密密钥
     */
    private fun generateAesSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PP_KEYSTORE_TYPE)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            PP_KEYSTORE_AES_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }
}