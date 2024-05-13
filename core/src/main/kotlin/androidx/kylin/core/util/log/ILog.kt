package androidx.kylin.core.util.log

import androidx.kylin.core.extension.toJson

/**
 * 日志接口
 * @author RAE
 * @date 2024/01/12
 */
interface ILog {

    /** 日志名称 */
    val name: String

    //================================================================
    //  默认日志
    //================================================================

    /** 记录日志 */
    fun log(level: LogLevel, message: String, tag: String = name, throwable: Throwable? = null)

    /** 写入日志文件 */
    fun file(level: LogLevel, message: String, tag: String = name, throwable: Throwable? = null)

    /** 调试日志 */
    fun debug(message: String) = log(LogLevel.DEBUG, message)

    /** 信息日志 */
    fun info(message: String) = log(LogLevel.INFO, message)

    /** 警告日志 */
    fun warn(message: String) = log(LogLevel.WARN, message)

    /** 错误日志 */
    fun error(message: String) = log(LogLevel.ERROR, message)

    /** 错误日志，消息=message+throwable.message */
    fun error(message: String, throwable: Throwable) {
        log(LogLevel.ERROR, "$message: ${throwable.message}", name, throwable)
    }

    /** 输出对象，默认DEBUG级别。 */
    fun <T : Any> json(any: T?) = debug(any?.toJson() ?: "NULL")

    //================================================================
    //  支持TAG日志
    //================================================================

    /** 调试日志 */
    fun debug(tag: String, message: String) = log(LogLevel.DEBUG, message, tag)

    /** 信息日志 */
    fun info(tag: String, message: String) = log(LogLevel.INFO, message, tag)

    /** 警告日志 */
    fun warn(tag: String, message: String) = log(LogLevel.WARN, message, tag)

    /** 错误日志 */
    fun error(tag: String, message: String) = log(LogLevel.ERROR, message, tag)

    /** 错误日志，消息=message+throwable.message */
    fun error(tag: String, message: String, throwable: Throwable) {
        log(LogLevel.ERROR, "$message: ${throwable.message}", tag, throwable)
    }
}