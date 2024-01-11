package androidx.kylin.core

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * 测试基类
 * @author RAE
 * @date 2024/01/11
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, shadows = [ShadowLog::class])
abstract class BaseTest