/**
 * Kotlin extension function for View
 * @author rae
 * @copyright https://github.com/raedev
 */
@file:Suppress("NOTHING_TO_INLINE")

package androidx.kylin.core.view

import android.view.View

/** Whether the view is gone */
inline fun View?.isGone(): Boolean = this?.visibility == View.GONE

/** Whether the view is isVisible */
inline fun View?.isVisible(): Boolean = this?.visibility == View.VISIBLE

/** Whether the view is isInvisible */
inline fun View?.isInvisible(): Boolean = this?.visibility == View.INVISIBLE

/** Auto set the visibility When the view is invisible */
inline fun View?.ifInvisible(visible: Boolean = false) {
    this?.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

/** Auto set the visibility When the view is visible */
inline fun View?.ifVisible(visible: Boolean = true) {
    this?.visibility = if (visible) View.VISIBLE else View.GONE
}

/** Show the view */
inline fun View?.show() = ifVisible()

/** Hide the view */
inline fun View?.hide() = ifVisible(false)

/** Toggle the view */
inline fun View?.toggle() = ifVisible(!isVisible())


