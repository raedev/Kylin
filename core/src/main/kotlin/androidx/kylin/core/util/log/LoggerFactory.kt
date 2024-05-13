package androidx.kylin.core.util.log

import java.util.concurrent.ConcurrentHashMap

/**
 * 日志工厂
 * @author RAE
 * @date 2024/01/12
 */
object LoggerFactory {

    private val loggerMap = ConcurrentHashMap<String, ILog>()


    /** 默认的日志名称 */
    var name: String = "kylin"
        set(value) {
            // 更新默认日志名称
            val log = loggerMap[field]
            if (log is Logger) {
                loggerMap.remove(field)
                log.name = value
                loggerMap[value] = log
            }
            field = value
        }


    init {
        /** 默认的日志记录 */
        loggerMap[name] = LoggerWrapper(name)
    }

    private class LoggerWrapper(override var name: String) : ILog {
        /** 日志集合 */
        val logs = mutableListOf<Logger>(AndroidLogger(name))

        override fun log(level: LogLevel, message: String, tag: String, throwable: Throwable?) {
            // 分发日志
            logs.forEach {
                it.log(level, message, tag, throwable)
            }
        }

        override fun file(level: LogLevel, message: String, tag: String, throwable: Throwable?) {
            // 分发日志
            logs.forEach {
                it.file(level, message, tag, throwable)
            }
        }
    }


    /** 获取日志接口 */
    fun getLogger(name: String? = null): ILog {
        val key = name ?: this.name
        if (loggerMap.containsKey(key)) return loggerMap[key]!!
        loggerMap[key] = LoggerWrapper(name ?: this.name)
        return loggerMap[key]!!
    }

    /** 添加日志 */
    fun appendLogger(source: ILog, block: (ILog) -> Logger) {
        if (source is LoggerWrapper) {
            source.logs.add(block(source))
        }
    }
}