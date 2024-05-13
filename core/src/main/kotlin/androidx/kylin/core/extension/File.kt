package androidx.kylin.core.extension

import java.io.File

/** 判断文件存在 */
fun File?.isExist(): Boolean = this?.exists() ?: false

/** 判断文件不存在 */
fun File?.isNotExist(): Boolean = !isExist()

/** 从字符串实例化成文件 */
fun String.toFile(): File = File(this)
