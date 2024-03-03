package androidx.kylin.core.util.security

/**
 * 填充方式，常用是NoPadding、PKCS5Padding，RSA一般是PKCS1Padding。
 * @author RAE
 * @date 2024/01/13
 */
enum class Paddings(private val value: String? = null) {


    NoPadding,

    PKCS5Padding,

    /**
     * - AES算法，API 1+
     * - DES算法，API 1+
     * - DESede算法，API 1+
     * - BLOWFISH算法，API 10+
     * */
    ISO10126Padding,

    /** RSA算法，API 1+ */
    OAEPPadding,

    /** RSA算法，API 1+ */
    PKCS1Padding,

    /** RSA算法, API 10+ */
    OAEPwithSHA1andMGF1Padding("OAEPwithSHA-1andMGF1Padding"),

    /** RSA算法, API 10+ */
    OAEPwithSHA256andMGF1Padding("OAEPwithSHA-256andMGF1Padding"),

    /** RSA算法, API 23+ */
    OAEPwithSHA224andMGF1Padding("OAEPwithSHA-224andMGF1Padding"),

    /** RSA算法, API 23+ */
    OAEPwithSHA384andMGF1Padding("OAEPwithSHA-384andMGF1Padding"),

    /** RSA算法, API 23+ */
    OAEPwithSHA512andMGF1Padding("OAEPwithSHA-512andMGF1Padding");

    override fun toString(): String = value ?: name
}