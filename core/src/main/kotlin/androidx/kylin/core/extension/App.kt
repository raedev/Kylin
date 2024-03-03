/**
 *  App.Kt
 * @author rae
 * @copyright https://github.com/raedev
 */
package androidx.kylin.core.extension

import androidx.kylin.core.MagicValue
import androidx.kylin.core.app.KylinGlobe
import androidx.kylin.core.util.PreferencesUtils
import androidx.kylin.core.util.log.ILog
import androidx.kylin.core.util.log.LoggerFactory

/** 全局应用程序 */
fun app() = KylinGlobe.application

/** 全局配置：设置配置 */
fun config(vararg pairs: Pair<String, Any?>) = PreferencesUtils.put(*pairs)

/** 全局配置：设置配置 */
fun config(key: String, value: Any) = PreferencesUtils.put(key, value)

/** 全局配置：获取配置 */
fun config(key: String): MagicValue = PreferencesUtils.getValue(key)

/** 全局日志 */
fun logger(): ILog = LoggerFactory.getLogger(null)

/** 全局日志 */
fun logger(name: String): ILog = LoggerFactory.getLogger(name)
