/**
 * Kotlin extension function for View
 * @author rae
 * @copyright https://github.com/raedev
 */

package androidx.kylin.core.extension

import android.view.View
import androidx.core.view.isVisible
import androidx.kylin.core.util.ViewUtils

/** View是否隐藏 */
inline var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

/** View是否可见 */
inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

/** View是否无形 */
inline var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

/** 隐藏View */
fun View?.gone() = visible(false)

/** 显示或隐藏View */
fun View?.visible(visible: Boolean = true) {
    this?.let { ViewUtils.setVisible(it, visible) }
}

/** 显示或无形View */
fun View?.invisible(visible: Boolean = false) {
    this?.let { ViewUtils.setInVisible(it, visible) }
}

/** 切换显示或隐藏 */
fun View?.toggleVisible() {
    this?.let {
        it.isVisible
        if (it.isShown) gone() else visible()
    }
}

/** 布局Id转View */
inline fun Int.inflate(): View {
    return ViewUtils.inflate(this)
}



