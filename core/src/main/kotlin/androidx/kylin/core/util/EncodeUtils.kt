@file:Suppress("MemberVisibilityCanBePrivate")

package androidx.kylin.core.util

import android.os.Build
import android.text.Html
import android.util.Base64
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset

/**
 * 编码相关工具类，提供URL编码、Base64编码、HTML编码等
 * @author rae
 * @data 2024/01/11
 * @copyright https://github.com/raedev
 */
object EncodeUtils {

    /**
     * Url编码
     *
     * @param input  待编码文本
     * @param charset 字符集
     */
    @JvmOverloads
    fun urlEncode(input: String, charset: Charset = Charsets.UTF_8): String {
        if (input.isBlank()) return input
        return runCatching {
            URLEncoder.encode(input, charset.name())
        }.getOrDefault(input)
    }

    /**
     * URL解码
     *
     * @param input  待编码文本
     * @param charset 字符集
     */
    @JvmOverloads
    fun urlDecode(input: String, charset: Charset = Charsets.UTF_8): String {
        if (input.isBlank()) return input
        return runCatching {
            val safeInput = input.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25")
                .replace("\\+".toRegex(), "%2B")
            URLDecoder.decode(safeInput, charset.name())
        }.getOrDefault(input)
    }

    /**
     * Base64编码
     * @param input 文本
     */
    fun base64Encode(input: String): ByteArray? {
        return base64Encode(input.toByteArray())
    }

    /**
     * Base64编码
     * @param input Byte 数组
     */
    fun base64Encode(input: ByteArray?): ByteArray? {
        return if (input?.isEmpty() == true) null else Base64.encode(input, Base64.NO_WRAP)
    }

    /**
     * Base64编码
     *
     * @param input byte 数据
     */
    fun base64Encode2String(input: String?): String? {
        return input?.let { base64Encode2String(it.toByteArray()) }
    }

    /**
     * Base64编码
     *
     * @param input byte 数据
     */
    fun base64Encode2String(input: ByteArray?): String? {
        return if (input?.isEmpty() == true) null else Base64.encodeToString(input, Base64.NO_WRAP)
    }

    /**
     * Base64解码
     * @param input 文本
     */
    fun base64Decode(input: String?): ByteArray? {
        return if (input.isNullOrBlank()) null else Base64.decode(input, Base64.NO_WRAP)
    }

    /**
     * Base64解码
     * @param input 文本
     */
    fun base64Decode(input: ByteArray?): ByteArray? {
        return if (input?.isEmpty() == true) null else Base64.decode(input, Base64.NO_WRAP)
    }


    /**
     * Base64解码
     * @param input 文本
     */
    fun base64Decode2String(input: String?): String? {
        return base64Decode(input)?.toString(Charsets.UTF_8)
    }

    /**
     * HTML编码
     * @param input 文本
     */
    fun htmlEncode(input: CharSequence?): String? {
        if (input.isNullOrBlank()) return null
        val sb = StringBuilder()
        var c: Char
        var i = 0
        val len = input.length
        while (i < len) {
            c = input[i]
            when (c) {
                '<' -> sb.append("&lt;") //$NON-NLS-1$
                '>' -> sb.append("&gt;") //$NON-NLS-1$
                '&' -> sb.append("&amp;") //$NON-NLS-1$
                '\'' ->
                    //http://www.w3.org/TR/xhtml1
                    // The named character reference &apos; (the apostrophe, U+0027) was
                    // introduced in XML 1.0 but does not appear in HTML. Authors should
                    // therefore use &#39; instead of &apos; to work as expected in HTML 4
                    // user agents.
                    sb.append("&#39;") //$NON-NLS-1$
                '"' -> sb.append("&quot;") //$NON-NLS-1$
                else -> sb.append(c)
            }
            i++
        }
        return sb.toString()
    }

    /**
     * HTML解码
     * @param input 文本
     */
    fun htmlDecode(input: String?): CharSequence? {
        if (input.isNullOrBlank()) return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(input)
        }
    }

    /**
     *  二进制编码
     * @param input 文本
     * @return binary string
     */
    fun binaryEncode(input: String?): String? {
        if (input.isNullOrBlank()) return input
        val sb = StringBuilder()
        for (i in input.toCharArray()) {
            sb.append(Integer.toBinaryString(i.code)).append(" ")
        }
        return sb.deleteCharAt(sb.length - 1).toString()
    }


    /**
     *  二进制解码
     * @param input 文本
     * @return binary string
     */
    fun binaryDecode(input: String?): String? {
        if (input.isNullOrBlank()) return input
        val splits = input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (split in splits) {
            sb.append(split.toInt(2).toChar())
        }
        return sb.toString()
    }
}
