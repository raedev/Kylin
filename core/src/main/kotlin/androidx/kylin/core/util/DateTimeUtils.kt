package androidx.kylin.core.util

import android.annotation.SuppressLint
import androidx.kylin.core.enums.DateFormatStyle
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 日期工具
 * @author RAE
 * @date 2024/03/18
 */
@SuppressLint("SimpleDateFormat")
object DateTimeUtils {

    private val INSTANCE = SimpleDateFormat(DateFormatStyle.DateTime.value)

    // 格式化给定模式与当前时间或指定时间，并返回格式化后的字符串
    fun format(pattern: String, time: Long = System.currentTimeMillis()): String {
        INSTANCE.applyPattern(pattern)
        return INSTANCE.format(time)
    }

    /**
     * 文本转日期
     */
    fun parse(pattern: String, time: String?): Date? {
        time ?: return null
        INSTANCE.applyPattern(pattern)
        return INSTANCE.parse(time)
    }


    /** 格式化日期 */
    private fun format(formatter: DateFormatStyle) = format(formatter.value)

    /** 格式化当前日志 */
    fun now(format: DateFormatStyle = DateFormatStyle.DateTime): String = format(format)

}