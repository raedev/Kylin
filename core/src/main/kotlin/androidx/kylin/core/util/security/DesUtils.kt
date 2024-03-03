package androidx.kylin.core.util.security

import androidx.kylin.core.util.EncodeUtils
import androidx.kylin.core.util.security.AesUtils.template
import javax.crypto.Cipher

/**
 * DES全称为Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法。
 * 1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
 * 需要注意的是，在某些文献中，作为算法的DES称为数据加密算法（Data Encryption Algorithm,DEA），已与作为标准的DES区分开来。
 * @author RAE
 * @date 2024/01/13
 */
object DesUtils {

    /**
     * DES加密
     * @param data 加密文本
     * @param key 加密密码
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     */
    fun encrypt(
        data: ByteArray,
        key: ByteArray,
        iv: ByteArray? = null,
        transformation: Transformation = Transformation.DesECBPKCS5Padding,
    ): ByteArray? = template(data, key, iv, transformation, Cipher.ENCRYPT_MODE)

    /**
     * DES加密
     * @param data 加密文本，默认UTF-8
     * @param key 加密密码，默认UTF-8
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     * @param mode 编码方式，默认是Base64
     */
    fun encrypt(
        data: String,
        key: String,
        iv: String? = null,
        transformation: Transformation = Transformation.DesECBPKCS5Padding,
        mode: EncodeMode = EncodeMode.BASE64
    ): String? = template(data, key, iv, transformation, Cipher.ENCRYPT_MODE, mode)

    /**
     * DES解密
     * @param data 加密文本
     * @param key 加密密码
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     */
    fun decrypt(
        data: ByteArray,
        key: ByteArray,
        iv: ByteArray? = null,
        transformation: Transformation = Transformation.DesECBPKCS5Padding,
    ): ByteArray? = template(data, key, iv, transformation, Cipher.DECRYPT_MODE)


    /**
     * DES解密
     * @param data 加密文本，默认UTF-8
     * @param key 加密密码，默认UTF-8
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     * @param mode 编码方式，默认是Base64
     */
    fun decrypt(
        data: String,
        key: String,
        iv: String? = null,
        transformation: Transformation = Transformation.DesECBPKCS5Padding,
        mode: EncodeMode = EncodeMode.BASE64
    ): String? {
        if (mode == EncodeMode.BASE64) {
            return template(
                EncodeUtils.base64Decode(data) ?: return null,
                key.toByteArray(),
                iv?.toByteArray(),
                transformation,
                Cipher.DECRYPT_MODE
            )?.toString(Charsets.UTF_8)
        }
        return template(data, key, iv, transformation, Cipher.DECRYPT_MODE)
    }
}