/**
 * Text.kt
 * @author rae
 * @copyright [https://github.com/raedev](https://github.com/raedev)
 */
package androidx.kylin.core.extension

import androidx.kylin.core.util.JsonUtils
import java.io.File

/** 字符串是否不为空 */
fun String?.isNotNull(): Boolean = !this.isNullOrEmpty()

/** 当字符串为空时返回默认值 */
fun String?.nullOrEmptyDefault(default: String): String =
    if (this.isNullOrBlank()) default else this

/** 从字符串实例化成文件 */
fun String.toFile(): File = File(this)

/** JSON转对象 */
fun <T : Any> String?.toBean(clazz: Class<T>): T? {
    return this?.let { JsonUtils.fromJson(it, clazz) }
}