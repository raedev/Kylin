package androidx.kylin.core.util.security

/**
 * Android支持的算法，常用是AES、DES、RSA
 * @author RAE
 * @date 2024/01/13
 */
enum class Algorithm(private val value: String? = null) {
    AES,
    AES128("AES_128"),
    AES256("AES_256"),
    ARC4,
    BLOWFISH,
    ChaCha20,
    DES,
    DESede,
    RSA;

    override fun toString(): String = value ?: name
}