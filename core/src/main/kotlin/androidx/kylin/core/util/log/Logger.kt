package androidx.kylin.core.util.log

import android.util.Log
import androidx.kylin.core.enums.DateFormatter
import androidx.kylin.core.extension.app
import androidx.kylin.core.extension.now
import androidx.kylin.core.util.DateTimeUtils
import java.io.File
import kotlin.concurrent.thread

/**
 * 日志记录
 * @author RAE
 * @date 2024/01/12
 */
abstract class Logger(
    override var name: String
) : ILog {

    /**
     * 设置记录日志级别，如果日志小于设置的级别则不会输出
     */
    var level: LogLevel = LogLevel.DEBUG

    override fun log(level: LogLevel, message: String, tag: String, throwable: Throwable?) {
        // 不记录日志
        if (level.level < this.level.level) return
        val tagName = if (tag != name) "$name.$tag" else name
        handleLog(level, tagName, message, throwable)
    }

    override fun file(level: LogLevel, message: String, tag: String, throwable: Throwable?) {
        log(level, message, tag, throwable)
        val threadId = Thread.currentThread().id
        // 写文件
        thread {
            runCatching {
                val now = now()
                val date = DateTimeUtils.now(DateFormatter.Date)
                val dir = app().getExternalFilesDir("log") ?: File(app().filesDir, "log")
                val file = File(dir, date)
                val sb = StringBuffer()
                    .append("$now $threadId ${level.name} $tag: $message\n")
                    .append(throwable?.stackTraceToString() ?: "")
                file.appendText(sb.toString())
            }
        }
    }

    /**
     * 记录错误日志
     */
    open fun handleLog(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
        // 错误日志单独记录
        if (level == LogLevel.ERROR) return handleErrorLog(tag, message, throwable)
    }

    /**
     * 记录错误日志
     */
    open fun handleErrorLog(tag: String, message: String, throwable: Throwable?) = Unit
}


/**
 * Android系统日志
 * @author RAE
 * @date 2024/01/12
 */
internal class AndroidLogger(name: String) : Logger(name) {

    override fun handleLog(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
        when (level) {
            LogLevel.DEBUG -> Log.d(tag, message)
            LogLevel.INFO -> Log.i(tag, message)
            LogLevel.WARN -> Log.w(tag, message, throwable)
            LogLevel.ERROR -> Log.e(tag, message, throwable)
        }
    }
}

/**
 * Kotlin 日志
 * @author RAE
 * @date 2024/01/12
 */
class KotlinLogger(name: String) : Logger(name) {

    override fun handleLog(level: LogLevel, tag: String, message: String, throwable: Throwable?) {
        println("$level $tag: $message")
        throwable?.printStackTrace()
    }
}