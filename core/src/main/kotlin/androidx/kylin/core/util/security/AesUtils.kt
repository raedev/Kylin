package androidx.kylin.core.util.security

import androidx.kylin.core.util.EncodeUtils
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES 高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法，
 * 是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
 * 经过五年的甄选流程，高级加密标准由美国国家标准与技术研究院（NIST）于2001年11月26日发布于FIPS PUB 197，
 * 并在2002年5月26日成为有效的标准。2006年，高级加密标准已然成为对称密钥加密中最流行的算法之一。
 * @author RAE
 * @date 2024/01/13
 */
object AesUtils {

    /**
     * AES加密
     * @param data 加密文本
     * @param key 加密密码
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     */
    fun encrypt(
        data: ByteArray,
        key: ByteArray,
        iv: ByteArray? = null,
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
    ): ByteArray? = template(data, key, iv, transformation, Cipher.ENCRYPT_MODE)

    /**
     * AES加密
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
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
        mode: EncodeMode = EncodeMode.BASE64
    ): String? = template(data, key, iv, transformation, Cipher.ENCRYPT_MODE, mode)

    /**
     * AES解密
     * @param data 加密文本
     * @param key 加密密码
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     */
    fun decrypt(
        data: ByteArray,
        key: ByteArray,
        iv: ByteArray? = null,
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
    ): ByteArray? = template(data, key, iv, transformation, Cipher.DECRYPT_MODE)


    /**
     * AES解密
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
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
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


    /**
     * AES加密
     * @param data 加密文本
     * @param key 加密密码
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     */
    internal fun template(
        data: ByteArray,
        key: ByteArray,
        iv: ByteArray? = null,
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
        mode: Int = Cipher.ENCRYPT_MODE
    ): ByteArray? {
        return runCatching {
            if (data.isEmpty() || key.isEmpty()) return@runCatching null
            val cipher = Cipher.getInstance(transformation.toString())
            val algorithm = transformation.algorithm.toString()
            val spec = when (transformation.algorithm) {
                Algorithm.DES -> {
                    val desKey = DESKeySpec(key)
                    val keyFactory = SecretKeyFactory.getInstance(algorithm)
                    keyFactory.generateSecret(desKey)
                }

                else -> SecretKeySpec(key.fillLength(), algorithm)
            }

            if (transformation.modes == Modes.CBC) {
                if (iv?.size != 16) throw IllegalArgumentException("Wrong IV length: must be 16 bytes long")
                val param = IvParameterSpec(iv)
                cipher.init(mode, spec, param)
            } else {
                cipher.init(mode, spec)
            }
            return cipher.doFinal(data)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
    }

    internal fun ByteArray.fillLength(length: Int? = null): ByteArray {
        if (size > 32) throw IllegalArgumentException("key size must be in 16/24/32 bytes")
        val newSize = when {
            size > 24 -> 32
            size > 16 -> 24
            else -> 16
        }
        return this.copyOf(length ?: newSize)
    }

    /**
     * AES加密
     * @param data 加密文本，默认UTF-8
     * @param key 加密密码，默认UTF-8
     * @param iv 偏移量（ECB模式不用填）
     * @param transformation 模式，默认是：Transformation.AESECBPKCS5Padding(AES/ECB/PKCS5Padding)
     * @param mode 编码方式，默认是Base64
     */
    internal fun template(
        data: String,
        key: String,
        iv: String? = null,
        transformation: Transformation = Transformation.AesECBPKCS5Padding,
        mode: Int = Cipher.ENCRYPT_MODE,
        encodeMode: EncodeMode = EncodeMode.BASE64
    ): String? {
        val b =
            template(
                data.toByteArray(),
                key.toByteArray(),
                iv?.toByteArray(),
                transformation,
                mode
            )
        return b?.let {
            if (encodeMode == EncodeMode.BASE64)
                EncodeUtils.base64Encode2String(it)
            else
                HexUtils.toString(it)
        }
    }
}