package androidx.kylin.core.util

import androidx.kylin.core.BaseTest
import org.junit.Test

/**
 * EncodeUtils 测试类
 * @author RAE
 * @date 2024/01/11
 */
class EncodeUtilsTest : BaseTest() {

    @Test
    fun testUrl() {
        val encodeText = "工具类测试"
        val decodeText = "%E5%B7%A5%E5%85%B7%E7%B1%BB%E6%B5%8B%E8%AF%95"
        assert(EncodeUtils.urlEncode(encodeText) == decodeText)
        assert(EncodeUtils.urlDecode(decodeText) == encodeText)
    }

    @Test
    fun testBase64() {
        val encodeText = "工具类测试"
        val decodeText = "5bel5YW357G75rWL6K+V"
        assert(EncodeUtils.base64Encode2String(encodeText) == decodeText)
        assert(EncodeUtils.base64Decode2String(decodeText) == encodeText)
    }

    @Test
    fun testHtml() {
        val encodeText = "<a>工具类测试</a>"
        val decodeText = "&lt;a&gt;工具类测试&lt;/a&gt;"
        assert(EncodeUtils.htmlEncode(encodeText) == decodeText)
        assert(EncodeUtils.htmlDecode(decodeText).toString() == encodeText)
    }

    @Test
    fun testBinaryDecode() {
        val encodeText = "二进制"
        val decodeText = "100111010001100 1000111111011011 101001000110110"
        assert(EncodeUtils.binaryEncode(encodeText) == decodeText)
        assert(EncodeUtils.binaryDecode(decodeText) == encodeText)
    }
}