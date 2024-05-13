package androidx.kylin.core

import androidx.kylin.core.app.KylinGlobe
import androidx.kylin.core.extension.logger
import androidx.kylin.core.util.log.KotlinLogger
import androidx.kylin.core.util.log.LoggerFactory
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * 测试基类
 * @author RAE
 * @date 2024/01/11
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, shadows = [ShadowLog::class])
abstract class BaseTest {

    init {
        // 初始化
        KylinGlobe.initialize(RuntimeEnvironment.getApplication())
        LoggerFactory.name = "KylinUnitTest"
        LoggerFactory.appendLogger(logger()) { KotlinLogger(it.name) }
    }
}