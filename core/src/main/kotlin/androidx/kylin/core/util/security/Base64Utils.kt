package androidx.kylin.core.util.security

import android.util.Base64
import java.nio.charset.Charset

/**
 * Base64 是一种基于64个可打印字符来表示二进制数据的表示方法。
 * @author RAE
 * @date 2024/01/14
 */
object Base64Utils {

    /**
     * Base64编码
     * @param input 文本
     */
    fun encode(input: String, charset: Charset = Charsets.UTF_8): String? {
        return encode(input.toByteArray(charset))?.toString(charset)
    }

    /**
     * Base64编码
     * @param input Byte 数组
     */
    fun encode(input: ByteArray?): ByteArray? {
        return if (input?.isEmpty() == true) null else Base64.encode(input, Base64.NO_WRAP)
    }


    /**
     * Base64解码
     * @param input 文本
     */
    fun decode(input: String?, charset: Charset = Charsets.UTF_8): String? {
        return if (input.isNullOrBlank()) null else Base64.decode(input, Base64.NO_WRAP)
            .toString(charset)
    }

    /**
     * Base64解码
     * @param input 文本
     */
    fun decode(input: ByteArray?): ByteArray? {
        return if (input?.isEmpty() == true) null else Base64.decode(input, Base64.NO_WRAP)
    }

}