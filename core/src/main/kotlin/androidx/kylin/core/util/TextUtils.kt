package androidx.kylin.core.util

/**
 * 文本处理
 * @author RAE
 * @date 2024/01/13
 */
object TextUtils {
    /**
     * Return whether the string is null or white space.
     *
     * @param  text The string.
     * @return `true`: yes<br></br> `false`: no
     */
    fun isSpace(text: String?): Boolean {
        if (text.isNullOrBlank()) return true
        var i = 0
        val len = text.length
        while (i < len) {
            if (!Character.isWhitespace(text[i])) {
                return false
            }
            ++i
        }
        return true
    }
}