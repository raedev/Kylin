package androidx.kylin.core.util.security

import androidx.kylin.core.util.TextUtils
import java.util.Locale

/**
 * 十六进制（英语：Hexadecimal，简写为Hex或下标16）在数学中是一种逢16进1的进位制。
 * 一般用数字0到9和字母A到F表示，其中：A~F相当于十进制的10~15，这些称作十六进制数字。
 * @author RAE
 * @date 2024/01/13
 */
object HexUtils {

    private val HEX_DIGITS_UPPER =
        charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    private val HEX_DIGITS_LOWER =
        charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    /**
     * 数组转HEX文本
     * @param bytes       The bytes.
     * @param isUpperCase True to use upper case, false otherwise.
     * @return hex string
     */
    fun toString(bytes: ByteArray, isUpperCase: Boolean = true): String {
        val hexDigits: CharArray = if (isUpperCase) HEX_DIGITS_UPPER else HEX_DIGITS_LOWER
        val len = bytes.size
        if (len <= 0) return ""
        val ret = CharArray(len shl 1)
        var i = 0
        var j = 0
        while (i < len) {
            ret[j++] = hexDigits[bytes[i].toInt() shr 4 and 0x0f]
            ret[j++] = hexDigits[bytes[i].toInt() and 0x0f]
            i++
        }
        return String(ret)
    }

    /**
     * Hex string to bytes.
     *
     * e.g. hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
     *
     * @param text The hex string.
     * @return the bytes
     */
    fun toByte(text: String): ByteArray {
        var hexString = text
        if (TextUtils.isSpace(hexString)) return ByteArray(0)
        var len = hexString.length
        if (len % 2 != 0) {
            hexString = "0$hexString"
            len += 1
        }
        val hexBytes = hexString.uppercase(Locale.getDefault()).toCharArray()
        val ret = ByteArray(len shr 1)
        var i = 0
        while (i < len) {
            ret[i shr 1] = (hex2Dec(hexBytes[i]) shl 4 or hex2Dec(hexBytes[i + 1])).toByte()
            i += 2
        }
        return ret
    }

    private fun hex2Dec(hexChar: Char): Int = when (hexChar) {
        in '0'..'9' -> hexChar.code - '0'.code
        in 'A'..'F' -> hexChar.code - 'A'.code + 10
        else -> throw IllegalArgumentException()
    }
}