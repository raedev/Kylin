package androidx.kylin.core.util.json

import androidx.kylin.core.util.GsonUtils
import java.lang.reflect.Type

/**
 * Gson序列化
 * @author RAE
 * @date 2024/01/12
 */
internal class GsonProvider : JsonProvider {

    override fun toJson(obj: Any): String {
        return GsonUtils.toJson(obj)
    }

    override fun <T> fromJson(json: String, type: Type): T {
        return GsonUtils.fromJson(json, type)
    }

    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        return GsonUtils.fromJson(json, clazz)
    }
}