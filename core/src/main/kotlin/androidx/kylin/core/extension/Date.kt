/**
 * 日期扩展函数
 * @author RAE
 * @date 2024/03/18
 */
package androidx.kylin.core.extension

import androidx.kylin.core.enums.DateFormatStyle
import androidx.kylin.core.util.DateTimeUtils
import java.util.Date

/**
 * 格式化当前日期
 * @param pattern 日期格式，可参考[DateFormatStyle]
 * @return 格式化
 */
fun Date.format(pattern: String) = DateTimeUtils.format(pattern, this.time)

/** 字符串转日期 */
fun CharSequence?.toDate(pattern: String): Date? = DateTimeUtils.parse(pattern, this?.toString())