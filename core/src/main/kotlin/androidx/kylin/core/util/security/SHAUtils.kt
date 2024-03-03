package androidx.kylin.core.util.security

import java.security.MessageDigest

/**
 * SHA是安全散列算法（英语：Secure Hash Algorithm，缩写为SHA）是一个密码散列函数家族，是FIPS所认证的安全散列算法。
 * 能计算出一个数字消息所对应到的，长度固定的字符串（又称消息摘要）的算法。且若输入的消息不同，它们对应到不同字符串的几率很高。
 * SHA家族的算法，由美国国家安全局（NSA）所设计，并由美国国家标准与技术研究院（NIST）发布，是美国的政府标准。
 * @author RAE
 * @date 2024/01/13
 */
object SHAUtils {

    // byte result

    fun sha512(data: ByteArray): ByteArray? = template(data, "SHA-512")
    fun sha384(data: ByteArray): ByteArray? = template(data, "SHA-384")
    fun sha256(data: ByteArray): ByteArray? = template(data, "SHA-256")
    fun sha224(data: ByteArray): ByteArray? = template(data, "SHA224")
    fun sha1(data: ByteArray): ByteArray? = template(data, "SHA-1")

    // hex string result

    fun sha512(data: String): String? = sha512(data.toByteArray()).toHex()
    fun sha384(data: String): String? = sha384(data.toByteArray()).toHex()
    fun sha256(data: String): String? = sha256(data.toByteArray()).toHex()
    fun sha224(data: String): String? = sha224(data.toByteArray()).toHex()
    fun sha1(data: String): String? = sha1(data.toByteArray()).toHex()

    private fun ByteArray?.toHex() = this?.let { HexUtils.toString(it) }

    private fun template(data: ByteArray, algorithm: String): ByteArray? = runCatching {
        val md = MessageDigest.getInstance(algorithm)
        md.update(data)
        return md.digest()
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}