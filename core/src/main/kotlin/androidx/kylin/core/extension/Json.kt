/**
 * JSON 工具类
 * @author RAE
 * @date 2024/01/12
 */
package androidx.kylin.core.extension

import androidx.kylin.core.util.JsonUtils
import kotlin.reflect.KClass

/** 对象转JSON */
fun Any?.toJson(): String? = this?.let { JsonUtils.toJson(this) }

/** 字符串转对象 */
fun <T : Any> CharSequence?.fromJson(kClass: KClass<T>): T? {
    return this?.let { JsonUtils.fromJson(it.toString(), kClass.java) }
}

///** 字符串转对象 */
//fun <T : Any> CharSequence?.toList(kClass: KClass<T>): List<T> {
//    return this?.let { JsonUtils.fromJson(it.toString(), kClass.java) } ?: emptyList<T>()
//}


