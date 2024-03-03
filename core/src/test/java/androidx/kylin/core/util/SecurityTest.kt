package androidx.kylin.core.util

import androidx.kylin.core.BaseTest
import androidx.kylin.core.util.security.AesUtils
import androidx.kylin.core.util.security.DesUtils
import androidx.kylin.core.util.security.HmacUtils
import androidx.kylin.core.util.security.Md5Utils
import androidx.kylin.core.util.security.SHAUtils
import androidx.kylin.core.util.security.Transformation
import org.junit.Test
import java.io.File

/**
 * androidx.kylin.core.util.security 包测试
 * @author RAE
 * @date 2024/01/13
 */
class SecurityTest : BaseTest() {

    @Test
    fun testMd5() {
        val md5 = Md5Utils.encrypt("passwd")
        assert("76A2173BE6393254E72FFA4D6DF1030A" == md5)
        val file = File("./src/test", "Md5.txt")
        val fileMd5 = Md5Utils.encrypt(file)
        assert("712A3A8BA9414D6A803507F31E5DF967" == fileMd5)
    }

    @Test
    fun testSHA() {
        val text = "passwd"
        assert(SHAUtils.sha1(text) == "30274C47903BD1BAC7633BBF09743149EBAB805F")
        assert(SHAUtils.sha224(text) == "D6BCBEB08AF49E291707FD135993A2ABDD544995D1695F29FC208975")
        assert(SHAUtils.sha256(text) == "0D6BE69B264717F2DD33652E212B173104B4A647B7C11AE72E9885F11CD312FB")
        assert(SHAUtils.sha384(text) == "851FAB8375C23D5749C028B18F2844E754C30AF17A11DF995EA4FAA8743EA67BB0EBF920332D9473D78BBDF9F47657CA")
        assert(SHAUtils.sha512(text) == "BF98889D70675DA7DC753CDF8AC78C49CDCCED21919DCE21B83DC8C81607E4EAF719AA5F9BC09BBE316D8E98F663C607CFC6EB4337EAA41601CD9BC303C5F583")
    }

    @Test
    fun testHmac() {
        val text = "passwd"
        val key = "key@123"
        assert(HmacUtils.md5(text, key) == "F3A21210D4212E2A7D70296CD66667BC")
        assert(HmacUtils.sha1(text, key) == "2CDFE36E11E564D2E8688DD541F10E0FC84355C5")
        assert(
            HmacUtils.sha224(
                text,
                key
            ) == "13230145880E0757FF2A3580082E19B18741D86961D370CC8A653C13"
        )
        assert(
            HmacUtils.sha256(
                text,
                key
            ) == "3110BF721918E898EB9385F48E3A8B37CD709558899F7561E8D877543DDBB7B8"
        )
        assert(
            HmacUtils.sha384(
                text,
                key
            ) == "2F236F8E77B0F431134048B2E0B155B1A7AE02C897FDEC22CFFA33101BF8687B304B1BAC51787AC023EC4AC0F3F0DCF5"
        )
        assert(
            HmacUtils.sha512(
                text,
                key
            ) == "F267A39ABDD2771E289270349DD04E90CD141C0B7BDA07EB13D5CDC8611D744A3C0EB2FB8136C6FC903ACCEA5962F9A632B51083587D2793DD78EE703F1C823E"
        )
    }

    @Test
    fun testAes() {
        val text = "KylinCoreAes"
        val key = "kylin@123456"
        // ECB
        assert(AesUtils.encrypt(text, key) == "1/SMq6GHRnEo63NHzHmoHw==")
        assert(AesUtils.decrypt("1/SMq6GHRnEo63NHzHmoHw==", key) == text)
        // CBC
        val iv = "mN1bI4kH8aT1sS1i"
        assert(
            AesUtils.encrypt(
                text,
                key,
                iv,
                Transformation.AesCBCPKCS5Padding
            ) == "5ftMYUb3Gn+ofs0mZVkPQQ=="
        )
        assert(
            AesUtils.decrypt(
                "5ftMYUb3Gn+ofs0mZVkPQQ==",
                key,
                iv,
                Transformation.AesCBCPKCS5Padding
            ) == text
        )
    }

    @Test
    fun testDes() {
        val text = "KylinCoreDES"
        val key = "kylin123456"
        // ECB
        assert(DesUtils.encrypt(text, key) == "lVJpJbQ3vYvqcZ9bQ9gY5Q==")
        assert(DesUtils.decrypt("lVJpJbQ3vYvqcZ9bQ9gY5Q==", key) == text)
        // CBC
        val iv = "mN1bI4kH8aT1sS1i"
        assert(
            AesUtils.encrypt(
                text,
                key,
                iv,
                Transformation.AesCBCPKCS5Padding
            ) == "5ftMYUb3Gn+ofs0mZVkPQQ=="
        )
        assert(
            AesUtils.decrypt(
                "5ftMYUb3Gn+ofs0mZVkPQQ==",
                key,
                iv,
                Transformation.AesCBCPKCS5Padding
            ) == text
        )
    }

}