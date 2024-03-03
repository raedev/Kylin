package androidx.kylin.core.util

import androidx.kylin.core.util.json.GsonProvider
import androidx.kylin.core.util.json.JsonProvider
import java.lang.reflect.Type

/**
 * Json序列化工具，默认是Gson解析，请在项目中引用库(com.google.code.gson:gson:xxx)
 * @author RAE
 * @date 2024/01/12
 */
object JsonUtils {

    /** JSON 提供者，默认是GSON */
    var provider: JsonProvider = GsonProvider()

    /** 序列化 */
    fun toJson(obj: Any): String {
        return provider.toJson(obj)
    }

    /** 反序列化 */
    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return provider.fromJson(json, clazz)
    }

    /** 反序列化 */
    fun <T> fromJson(json: String, type: Type): T {
        return provider.fromJson(json, type)
    }

    /** 复制对象 */
    fun <T : Any> copy(any: T): T {
        return provider.copy(any)
    }

}