package androidx.kylin.core.util

import android.content.Context
import androidx.core.content.edit
import androidx.kylin.core.MagicValue
import androidx.kylin.core.app.SharedPreferencesWrapper
import androidx.kylin.core.extension.app
import androidx.kylin.core.extension.toBean

/**
 * 偏好保存相关
 * @author RAE
 * @date 2024/01/12
 */
object PreferencesUtils {

    /** 默认的偏好提供者 */
    var provider: SharedPreferencesWrapper = makeDefault()

    /** 根据键值对保存 */
    fun put(vararg pairs: Pair<String, Any?>) = provider.putAll(*pairs)

    /** 根据key-value保存 */
    fun put(key: String, value: Any?) = put(key to value)

    /** 是否有键 */
    fun contains(key: String) = provider.contains(key)

    /** 移除键 */
    fun remove(key: String) = provider.edit { remove(key) }

    /** 获取所有 */
    fun all(): MutableMap<String, *> = provider.all

    /** 获取字符串类型值 */
    fun string(key: String, def: String? = null) = provider.getString(key, def)

    /** 获取布尔类型值 */
    fun bool(key: String, def: Boolean = false) = provider.getBoolean(key, def)

    /** 获取Int类型值 */
    fun int(key: String, def: Int = 0) = provider.getInt(key, def)

    /** 获取Double类型值 */
    fun double(key: String, def: Float = 0f) = provider.getFloat(key, def).toDouble()

    /** 获取Float类型值 */
    fun float(key: String, def: Float = 0f) = provider.getFloat(key, def)

    /** 获取Long类型值 */
    fun long(key: String, def: Long = 0L) = provider.getLong(key, def)

    /** 获取对象类型值 */
    fun <T : Any> bean(key: String, clazz: Class<T>): T? = string(key)?.let { it.toBean(clazz) }

    fun getValue(key: String): MagicValue = provider.getValue(key)

    /** 清除所有 */
    fun clear() = provider.edit { clear() }

    /** 清除满足过滤器的键 */
    fun clear(filter: (key: String) -> Boolean) = provider.edit {
        provider.all.keys.filter(filter).forEach {
            remove(it)
        }
    }

    /** 获取默认的偏好提供者 */
    private fun makeDefault(): SharedPreferencesWrapper {
        val name = app().packageName.replace(".", "_")
        val sharedPreferences = app().getSharedPreferences(name, Context.MODE_PRIVATE)
        return SharedPreferencesWrapper(sharedPreferences)
    }

}