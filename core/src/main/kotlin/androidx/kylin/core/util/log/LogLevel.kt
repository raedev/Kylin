package androidx.kylin.core.util.log

import android.util.Log

/**
 * 日志级别
 * @author RAE
 * @date 2024/01/12
 */
enum class LogLevel(val level: Int) {
    DEBUG(Log.DEBUG), INFO(Log.INFO), WARN(Log.WARN), ERROR(Log.ERROR)
}