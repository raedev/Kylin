package androidx.kylin.core.lang

import androidx.kylin.core.extension.toBean
import kotlin.reflect.KClass

/**
 * 魔术值，可变任意数据类型
 * @author RAE
 * @date 2024/01/12
 */
@Suppress("UNCHECKED_CAST")
class MagicValue(
    val value: Any?,
    val type: Class<*> = value?.javaClass ?: Any::class.java
) : Any() {

    /** 取String类型 */
    fun string(): String = value.toString()

    /** 取Bool类型 */
    fun bool(): Boolean = take(Boolean::class) {
        string().trim().let { it.equals("true", true) || it == "1" }
    }

    /** 取Int类型 */
    fun int(): Int = take(Int::class) { string().toInt() }

    /** 取Double类型 */
    fun double(): Double = take(Double::class) { string().toDouble() }

    /** 取Float类型 */
    fun float(): Float = take(Float::class) { string().toFloat() }

    /** 取Long类型 */
    fun long(): Long = take(Long::class) { string().toLong() }

    /** 转对象 */
    fun <T : Any> bean(clazz: Class<T>): T? = string().toBean(clazz)

    /** 是否为空 */
    fun isNull(): Boolean = value === null

    private inline fun <T : Any> take(clazz: KClass<T>, block: () -> T): T {
        return if (type == clazz.java) value as T else block()
    }

    override fun hashCode(): Int = value.hashCode()

    override infix fun equals(other: Any?): Boolean {
        if (isNull() && other == null) return true
        return value?.equals(other) ?: false
    }
}