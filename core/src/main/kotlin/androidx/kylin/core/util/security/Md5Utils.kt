package androidx.kylin.core.util.security

import java.io.File
import java.io.FileInputStream
import java.security.DigestInputStream
import java.security.MessageDigest

/**
 * MD5信息摘要算法（英语：MD5 Message-Digest Algorithm），一种被广泛使用的密码散列函数，
 * 可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一致。
 * @author RAE
 * @date 2024/01/13
 */
object Md5Utils {

    private val digest = MessageDigest.getInstance("MD5")

    /**
     * MD5加密
     */
    fun encrypt2Byte(data: ByteArray): ByteArray {
        if (data.isEmpty()) return data
        digest.update(data)
        return digest.digest()
    }

    /**
     * MD5加密(默认为32位大写）
     */
    fun encrypt(data: String, upper: Boolean = true): String {
        return HexUtils.toString(encrypt2Byte(data.toByteArray()), upper)
    }

    /**
     * 获取文件Md5
     */
    fun encrypt(file: File): String? {
        if (!file.exists()) return null
        return runCatching {
            val fis = FileInputStream(file)
            val dis = DigestInputStream(fis, digest)
            val buffer = ByteArray(256 * 1024)
            while (true) {
                if (dis.read(buffer) <= 0) break
            }
            HexUtils.toString(dis.messageDigest.digest())
        }.getOrNull()
    }

}