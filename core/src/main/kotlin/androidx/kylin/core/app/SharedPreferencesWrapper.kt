package androidx.kylin.core.app

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.kylin.core.MagicValue
import androidx.kylin.core.extension.toJson

/**
 * SharedPreferences 代理装饰类
 * @author RAE
 * @date 2024/01/12
 */
@Suppress("UNCHECKED_CAST")
open class SharedPreferencesWrapper(
    protected val wrapper: SharedPreferences
) : SharedPreferences by wrapper {

    /** 是否发生改变 */
    protected var isChanged: Boolean = false

    protected var map: Map<String, *>? = null

    // 设置方法全部统一到这个方法维护
    open fun putAll(vararg pairs: Pair<String, Any?>) = edit {
        for ((key, value) in pairs) {
            when (value) {
                null -> putString(key, null) // Any nullable type will suffice.
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Double -> putFloat(key, value.toFloat())
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Set<*> -> putStringSet(key, value as Set<String>)
                else -> {
                    // 其他类型序列化为JSON
                    val json = value.toJson()
                    putString(key, json)
                }
            }
        }
        isChanged = true
    }

    fun getValue(key: String): MagicValue {
        val map = if (map == null || isChanged) all.also {
            this.map = it
            isChanged = false
        } else map!!
        val value = map[key] ?: return MagicValue(null)
        return MagicValue(value)
    }

}