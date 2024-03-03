/**
 * Kotlin extension function for View
 * @author rae
 * @copyright https://github.com/raedev
 */

package androidx.kylin.core.extension

import android.view.View
import androidx.kylin.core.util.ViewUtils

/** View是否隐藏 */
fun View?.isGone(): Boolean = this?.visibility == View.GONE

/** View是否可见 */
fun View?.isVisible(): Boolean = this?.visibility == View.VISIBLE

/** View是否无形 */
fun View?.isInvisible(): Boolean = this?.visibility == View.INVISIBLE

/** 隐藏View */
fun View?.gone() = visible(false)

/** 显示或隐藏View */
fun View?.visible(visible: Boolean = false) {
    this?.let { ViewUtils.setVisible(it, visible) }
}

/** 显示或无形View */
fun View?.invisible(visible: Boolean = false) {
    this?.let { ViewUtils.setInVisible(it, visible) }
}

/** 切换显示或隐藏 */
fun View?.toggle() {
    if (isGone() || isInvisible()) visible() else gone()
}


