package androidx.kylin.core.util.json

import java.lang.reflect.Type

/**
 * Json接口
 * @author RAE
 * @date 2024/01/12
 */
interface JsonProvider {

    /**
     * 序列化
     */
    fun toJson(obj: Any): String

    /**
     * 反序列化
     */
    fun <T> fromJson(json: String, type: Type): T

    /**
     * 反序列化
     */
    fun <T> fromJson(json: String, clazz: Class<T>): T

    /**
     * 复制对象，序列化源对象后再反序列化
     */
    fun <T : Any> copy(obj: T): T {
        return fromJson(toJson(obj), obj.javaClass)
    }
}