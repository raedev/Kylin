/**
 *
 * @author RAE
 * @date 2024/01/12
 */
package androidx.kylin.core.extension

import androidx.kylin.core.util.JsonUtils

/** 对象转JSON */
fun Any?.toJson() = this?.let { JsonUtils.toJson(this) }

