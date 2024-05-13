package androidx.kylin.core.app

import android.app.Application
import androidx.core.content.FileProvider

/**
 * 文件提供者，实际用来获取全局的ApplicationContext
 * @author rae
 * @copyright https://github.com/raedev
 */
open class KylinCoreFileProvider : FileProvider() {

    override fun onCreate(): Boolean {
        if (context?.applicationContext is Application) {
            KylinGlobe.initialize(context!!.applicationContext as Application)
            return true
        }
        return false
    }
}