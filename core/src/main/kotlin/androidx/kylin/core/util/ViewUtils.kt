package androidx.kylin.core.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.kylin.core.extension.app

/**
 * View相关
 * @author RAE
 * @date 2024/01/12
 */
object ViewUtils {

    /** 设置View的可见状态 */
    private fun setVisibility(view: View, visibility: Int) {
        // 当不等于当前状态的时候才去更新
        if (view.visibility != visibility) view.visibility = visibility
    }

    /** 设置View的可见或隐藏状态（VISIBLE/GONE) */
    fun setVisible(view: View, visible: Boolean) {
        setVisibility(view, if (visible) View.VISIBLE else View.GONE)
    }

    /** 设置View的可见或隐藏状态（VISIBLE/INVISIBLE) */
    fun setInVisible(view: View, visible: Boolean) {
        setVisibility(view, if (visible) View.VISIBLE else View.INVISIBLE)
    }

    /** 布局Id转View */
    fun inflate(@LayoutRes id: Int, parent: ViewGroup? = null): View {
        return LayoutInflater.from(app()).inflate(id, parent)
    }
}