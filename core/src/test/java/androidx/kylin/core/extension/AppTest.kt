package androidx.kylin.core.extension

import androidx.kylin.core.BaseTest
import androidx.kylin.core.util.PreferencesUtils
import androidx.kylin.core.util.log.KotlinLogger
import androidx.kylin.core.util.log.LoggerFactory
import org.junit.Test

/**
 *
 * @author RAE
 * @date 2024/01/12
 */
class AppTest : BaseTest() {

    data class User(val name: String, val age: Int)

    @Test
    fun testApp() {
        val app = app()
        println(app)
    }

    @Test
    fun testConfig() {
        assert(config("myKey").isNull())
        config(
            "testKey" to 1,
            "testKey" to 2,
            "stringValue" to "KylinCore",
            "boolValue" to true,
            "doubleValue" to 3.14,
            "floatValue" to 3.14159f,
            "longValue" to 1024L
        )
        config("kylin", "core")
        // 基础数据类型
        assert(config("testKey").int() == 2)
        assert(config("stringValue").string() == "KylinCore")
        assert(config("stringValue") equals "KylinCore")
        assert(config("boolValue").bool())
        assert(config("doubleValue").double() == 3.14)
        assert(config("floatValue").float() == 3.14159f)
        assert(config("longValue").long() == 1024L)
        assert(config("kylin") equals "core")
        // 对象类型
        val user = User("rae", 18)
        config("user", user)
        config("user").bean(User::class.java)?.also { assert(it.name == "rae") }

        println(PreferencesUtils.all().toJson())
    }

    @Test
    fun testLogger() {
        LoggerFactory.appendLogger(logger("rae")) { KotlinLogger(it.name) }
        logger().debug("调试输出")
        logger().info("信息输出")
        logger().warn("警告输出")
        logger().error("错误输出")
        logger().debug("rae", "自定义TAG错误输出")
        logger().json(User("rae", 18))
        logger("rae").error("test", "RAE输出")
    }
}