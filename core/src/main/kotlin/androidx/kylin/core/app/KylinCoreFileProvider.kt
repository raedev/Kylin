package androidx.kylin.core.app

import androidx.core.content.FileProvider
import androidx.kylin.core.internal.GlobeApplication

/**
 * this file provider only do one thing that provide the application context.
 * @author rae
 * @copyright https://github.com/raedev
 */
class KylinCoreFileProvider : FileProvider() {

    override fun onCreate(): Boolean {
        GlobeApplication.init(context!!.applicationContext)
        return true
    }
}