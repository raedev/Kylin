package androidx.kylin.core.extension

import java.io.File

fun File?.isExist(): Boolean = this?.exists() ?: false

fun File?.isNotExist(): Boolean = !isExist()



