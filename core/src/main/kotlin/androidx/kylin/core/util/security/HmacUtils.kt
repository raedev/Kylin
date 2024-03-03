package androidx.kylin.core.util.security

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * HMAC是密钥散列消息认证码（Hash-based Message Authentication Code）的缩写，
 * 是一种通过特别计算方式之后产生的消息认证码（MAC），使用密码散列函数，同时结合一个加密密钥。
 * 它可以用来保证资料的完整性，同时可以用来作某个消息的身份验证。
 * @author RAE
 * @date 2024/01/13
 */
object HmacUtils {


    // byte result

    fun md5(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacMD5")
    fun sha512(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacSHA512")
    fun sha384(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacSHA384")
    fun sha256(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacSHA256")
    fun sha224(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacSHA224")
    fun sha1(data: ByteArray, key: ByteArray): ByteArray? = template(data, key, "HmacSHA1")

    // hex string result

    fun md5(data: String, key: String): String? =
        md5(data.toByteArray(), key.toByteArray()).toHex()

    fun sha512(data: String, key: String): String? =
        sha512(data.toByteArray(), key.toByteArray()).toHex()

    fun sha384(data: String, key: String): String? =
        sha384(data.toByteArray(), key.toByteArray()).toHex()

    fun sha256(data: String, key: String): String? =
        sha256(data.toByteArray(), key.toByteArray()).toHex()

    fun sha224(data: String, key: String): String? =
        sha224(data.toByteArray(), key.toByteArray()).toHex()

    fun sha1(data: String, key: String): String? =
        sha1(data.toByteArray(), key.toByteArray()).toHex()

    private fun template(data: ByteArray, key: ByteArray, algorithm: String): ByteArray? {
        return runCatching {
            if (data.isEmpty() || key.isEmpty()) return@runCatching null
            val mac = Mac.getInstance(algorithm)
            mac.init(SecretKeySpec(key, algorithm))
            return mac.doFinal(data)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
    }

    private fun ByteArray?.toHex() = this?.let { HexUtils.toString(it) }
}