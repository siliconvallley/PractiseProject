package com.xjsd.practiseproject.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

object KeyStoreHelper {
    // 密钥库类型
    private const val PP_KEYSTORE_TYPE = "AndroidKeyStore"
    // 密钥库别名
    private const val PP_KEYSTORE_ALIAS = "pp_keystore_alias"
    // 加密算法标准算法名称
    private const val PP_TRANSFORMATION = "RSA/ECB/PKCS1Padding"

    /**
     * 数据加密.
     *
     * @param data 原始数据，字符串
     * @return 加密数据，字节数组
     */
    fun encryptData(data: String): ByteArray {
        return encryptDataInternal(data.toByteArray())
    }

    /**
     * 数据解密.
     *
     * @param bytes 加密数据
     * @return 原始数据，字符串
     */
    fun decryptData(bytes: ByteArray): String {
        return String(decryptDataInternal(bytes))
    }

    /**
     * 数据加密.
     *
     * @param bytes 原始数据
     * @return 加密数据
     */
    private fun encryptDataInternal(bytes: ByteArray): ByteArray {
        return getPublicKey()?.run {
            val cipher = Cipher.getInstance(PP_TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, this)
            cipher.doFinal(bytes)
        } ?: byteArrayOf()
    }

    /**
     * 数据解密.
     *
     * @param bytes 加密数据
     * @return 原始数据
     */
    private fun decryptDataInternal(bytes: ByteArray): ByteArray {
        return getPrivateKey()?.run {
            val cipher = Cipher.getInstance(PP_TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, this)
            cipher.doFinal(bytes)
        } ?: byteArrayOf()
    }

    /**
     * 获取公钥.
     *
     * @return 公钥
     */
    private fun getPublicKey(): PublicKey? {
        val keyStore = KeyStore.getInstance(PP_KEYSTORE_TYPE).apply {
            load(null)
        }
        // 判断密钥是否存在
        if (!keyStore.containsAlias(PP_KEYSTORE_ALIAS)) {
            return generateKey().public
        }
        val entry = keyStore.getEntry(PP_KEYSTORE_ALIAS, null)
        if (entry !is KeyStore.PrivateKeyEntry) {
            return null
        }
        return entry.certificate.publicKey
    }

    /**
     * 获取私钥.
     *
     * @return 密钥
     */
    private fun getPrivateKey(): PrivateKey? {
        val keyStore = KeyStore.getInstance(PP_KEYSTORE_TYPE).apply {
            load(null)
        }
        // 判断密钥是否存在
        if (!keyStore.containsAlias(PP_KEYSTORE_ALIAS)) {
            return generateKey().private
        }
        val entry = keyStore.getEntry(PP_KEYSTORE_ALIAS, null)
        if (entry !is KeyStore.PrivateKeyEntry) {
            return null
        }
        return entry.privateKey
    }

    /**
     * 触发生成密钥对.
     *
     * 生成RSA 密钥对，包括公钥和私钥
     *
     * @return KeyPair 密钥对，包含公钥和私钥
     */
    private fun generateKey(): KeyPair {
        // 创建密钥生成器
        val keyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA,
            PP_KEYSTORE_TYPE
        )
        // 配置密钥生成器参数
        KeyGenParameterSpec.Builder(
            PP_KEYSTORE_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .build().run {
                keyPairGenerator.initialize(this)
            }
        // 生成密钥对
        return keyPairGenerator.generateKeyPair()
    }
}