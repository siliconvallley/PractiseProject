package com.xjsd.practiseproject.ext

import android.util.Base64
import java.nio.ByteBuffer

/**
 * FloatArray 转 ByteArray.
 *
 * @return ByteArray
 */
fun FloatArray.toByteArray(): ByteArray {
    val byteBuffer = ByteBuffer.allocate(this.size * 4)
    for (value in this) {
        byteBuffer.putFloat(value)
    }
    return byteBuffer.array()
}

/**
 * ByteArray 转 FloatArray.
 *
 * @return FloatArray
 */
fun ByteArray.toFloatArray(): FloatArray {
    val floatArray = FloatArray(this.size / 4)
    val byteBuffer = ByteBuffer.wrap(this)
    for (i in floatArray.indices) {
        floatArray[i] = byteBuffer.float
    }
    return floatArray
}

/**
 * FloatArray 转 Base64字符串.
 *
 * @return Base64字符串
 */
fun FloatArray.toBase64(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
}

/**
 * Base64字符串 转 FloatArray.
 *
 * @return FloatArray
 */
fun String.base64ToFloatArray(): FloatArray {
    return Base64.decode(this, Base64.DEFAULT).toFloatArray()
}

/**
 * ByteArray 转 Base64字符串.
 *
 * @return Base64字符串
 */
fun ByteArray.toBase64(): String {
    return Base64.encodeToString(this, Base64.DEFAULT)
}

/**
 * Base64字符串 转 ByteArray.
 *
 * @return ByteArray
 */
fun String.base64ToByteArray(): ByteArray {
    return Base64.decode(this, Base64.DEFAULT)
}

/**
 * ByteArray转Int.
 *
 * @param littleEndian
 * * true：小端 {最低有效字节存储在内存的最低地址处， 两个字节的顺序是：低字节在前，高字节在后。}
 * * false：大端 {最高有效字节存储在内存的最低地址处， 两个字节的顺序是：高字节在前，低字节在后。}
 * 默认：使用大端序
 */
fun ByteArray.toInt(littleEndian: Boolean = false): Int {
    return if (size == 2) {
        twoByteArrayToInt(this, littleEndian)
    } else {
        fourByteArrayToInt(this, littleEndian)
    }
}

/**
 * 双字节长度ByteArray转Int.
 */
private fun twoByteArrayToInt(bytes: ByteArray, littleEndian: Boolean = false): Int {
    if (bytes.size != 2) {
        return 0
    }
    return if (littleEndian) {
        (bytes[0].toInt() and 0xFF) or ((bytes[1].toInt() and 0xFF) shl 8)
    } else {
        ((bytes[0].toInt() and 0xFF) shl 8) or (bytes[1].toInt() and 0xFF)
    }
}

/**
 * 四字节长度ByteArray转Int.
 */
private fun fourByteArrayToInt(bytes: ByteArray, littleEndian: Boolean = false): Int {
    if (bytes.size != 4) {
        return 0
    }
    return if (littleEndian) {
        (bytes[0].toInt() and 0xFF) or
                ((bytes[1].toInt() and 0xFF) shl 8) or
                ((bytes[2].toInt() and 0xFF) shl 16) or
                ((bytes[3].toInt() and 0xFF) shl 24)
    } else {
        (bytes[0].toInt() and 0xFF shl 24) or
                (bytes[1].toInt() and 0xFF shl 16) or
                (bytes[2].toInt() and 0xFF shl 8) or
                (bytes[3].toInt() and 0xFF)
    }
}


/**
 * Int转ByteArray.
 *
 * @param size 转换为几个字节的字节数组，支持2、4
 * @param littleEndian
 * * true：小端 {最低有效字节存储在内存的最低地址处， 两个字节的顺序是：低字节在前，高字节在后。}
 * * false：大端 {最高有效字节存储在内存的最低地址处， 两个字节的顺序是：高字节在前，低字节在后。}
 * 默认：使用大端序
 */
fun Int.toByteArray(size: Int = 2, littleEndian: Boolean = false): ByteArray {
    if (size != 2 && size != 4) {
        return byteArrayOf()
    }
    return if (size == 2 && this in Short.MIN_VALUE..Short.MAX_VALUE) {
        intToTwoByteArray(this, littleEndian)
    } else {
        intToFourByteArray(this, littleEndian)
    }
}

/**
 * Int转两个字节的字节数组.
 */
private fun intToTwoByteArray(value: Int, littleEndian: Boolean): ByteArray {
    return if (littleEndian) {
        byteArrayOf(
            (value and 0xFF).toByte(),
            (value shr 8 and 0xFF).toByte()
        )
    } else {
        byteArrayOf(
            (value shr 8 and 0xFF).toByte(),
            (value and 0xFF).toByte()
        )
    }
}

/**
 * Int转四个字节的字节数组.
 */
private fun intToFourByteArray(value: Int, littleEndian: Boolean): ByteArray {
    return if (littleEndian) {
        byteArrayOf(
            (value and 0xFF).toByte(),
            (value shr 8 and 0xFF).toByte(),
            (value shr 16 and 0xFF).toByte(),
            (value shr 24 and 0xFF).toByte()
        )
    } else {
        byteArrayOf(
            (value shr 24 and 0xFF).toByte(),
            (value shr 16 and 0xFF).toByte(),
            (value shr 8 and 0xFF).toByte(),
            (value and 0xFF).toByte()
        )
    }
}


