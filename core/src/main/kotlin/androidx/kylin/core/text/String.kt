/**
 * String.kt
 * @author rae
 * @copyright [https://github.com/raedev](https://github.com/raedev)
 */
package androidx.kylin.core.text

import java.io.File

/** whether the string is not null or empty. */
inline fun String?.isNotNull(): Boolean = !this.isNullOrEmpty()

/** when the string is empty and then return the default value. */
inline fun String?.nullOrEmptyDefault(default: String): String =
    if (this.isNullOrEmpty()) default else this

/** covert the string to file. */
inline fun String.toFile(): File = File(this)