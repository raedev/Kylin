package androidx.kylin.core.util

import android.util.Log
import java.io.Closeable

/**
 * IO 流操作
 * @author RAE
 * @date 2024/01/11
 */
object IOUtils {

    const val TAG = "IOUtils"

    /**
     * 关闭IO流
     */
    fun close(vararg closeable: Closeable) {
        closeable.forEach {
            runCatching { it.close() }.onFailure { Log.e(TAG, "$it close error") }
        }
    }


}