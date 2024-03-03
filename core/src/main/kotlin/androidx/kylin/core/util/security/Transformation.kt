package androidx.kylin.core.util.security

/**
 * 转换方式
 * @author RAE
 * @date 2024/01/14
 */
open class Transformation(
    val algorithm: Algorithm,
    val modes: Modes,
    val paddings: Paddings
) {

    override fun toString(): String = "$algorithm/$modes/$paddings"

    /**
     * AES/CBC/NoPadding
     */
    object AesCBCNoPadding : Transformation(Algorithm.AES, Modes.CBC, Paddings.NoPadding)

    /**
     * AES/CBC/PKCS5Padding
     */
    object AesCBCPKCS5Padding : Transformation(Algorithm.AES, Modes.CBC, Paddings.PKCS5Padding)

    /**
     * AES/ECB/NoPadding
     */
    object AesECBNoPadding : Transformation(Algorithm.AES, Modes.ECB, Paddings.NoPadding)

    /**
     * AES/ECB/PKCS5Padding
     */
    object AesECBPKCS5Padding : Transformation(Algorithm.AES, Modes.ECB, Paddings.PKCS5Padding)


    /**
     * DES/CBC/NoPadding
     */
    object DesCBCNoPadding : Transformation(Algorithm.DES, Modes.CBC, Paddings.NoPadding)

    /**
     * DES/CBC/PKCS5Padding
     */
    object DesCBCNoPKCS5Padding : Transformation(Algorithm.DES, Modes.CBC, Paddings.PKCS5Padding)

    /**
     * DES/ECB/NoPadding
     */
    object DesECBNoPadding : Transformation(Algorithm.DES, Modes.ECB, Paddings.NoPadding)

    /**
     * DES/ECB/PKCS5Padding
     */
    object DesECBPKCS5Padding : Transformation(Algorithm.DES, Modes.ECB, Paddings.PKCS5Padding)

    /**
     * RSA/ECB/PKCS1Padding
     */
    object RsaECBPKCS5Padding : Transformation(Algorithm.DES, Modes.ECB, Paddings.PKCS1Padding)


}