/**
 *  App.Kt
 * @author rae
 * @copyright https://github.com/raedev
 */
package androidx.kylin.core.extension

import androidx.kylin.core.app.KylinGlobe
import androidx.kylin.core.lang.MagicValue
import androidx.kylin.core.util.DateTimeUtils
import androidx.kylin.core.util.PreferencesUtils
import androidx.kylin.core.util.log.ILog
import androidx.kylin.core.util.log.LoggerFactory

/** 全局应用程序 */
fun app() = KylinGlobe.getApp()

/** 全局配置：设置配置 */
fun config(vararg pairs: Pair<String, Any?>) = PreferencesUtils.put(*pairs)

/** 全局配置：设置配置 */
fun config(key: String, value: Any) = PreferencesUtils.put(key, value)

/** 全局配置：获取配置 */
fun config(key: String): MagicValue = PreferencesUtils.getValue(key)

/** 全局默认日志 */
fun logger(): ILog = LoggerFactory.getLogger()

/** 全局日志 */
fun logger(name: String): ILog = LoggerFactory.getLogger(name)

/** 当前时间，标准格式: yyyy-MM-dd HH:mm:ss */
fun now(): String = DateTimeUtils.now()