package androidx.kylin.core.enums

/**
 * 日期格式化样式
 * @author RAE
 * @date 2024/03/18
 */
enum class DateFormatStyle(var value: String) {
    Date("yyyy-MM-dd"),
    Time("HH:mm:ss"),
    DateTime("yyyy-MM-dd HH:mm:ss"),
    DateTimeWithoutSecond("yyyy-MM-dd HH:mm"),
    MonthDay("MM-dd"),
    MonthDayTime("MM-dd HH:mm"),
    MonthDayTimes("MM-dd HH:mm:ss");
}