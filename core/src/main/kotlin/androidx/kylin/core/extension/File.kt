package androidx.kylin.core.extension

import java.io.File

inline fun File?.isExist() = this?.exists() ?: false

inline fun File?.isNotExist() = !isExist()



