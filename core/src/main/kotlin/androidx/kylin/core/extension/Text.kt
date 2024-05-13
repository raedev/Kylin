/**
 * Text.kt
 * @author rae
 * @copyright [https://github.com/raedev](https://github.com/raedev)
 */
package androidx.kylin.core.extension

import androidx.kylin.core.util.JsonUtils

/** 字符串是否不为空 */
fun String?.isNotEmpty(): Boolean = !this.isNullOrEmpty()

/** 当字符串为空时返回默认值 */
fun String?.emptyOrDefault(default: String): String {
    return if (this.isNullOrBlank()) default else this
}

/** JSON转对象 */
fun <T : Any> String?.toBean(clazz: Class<T>): T? {
    return this?.let { JsonUtils.fromJson(it, clazz) }
}