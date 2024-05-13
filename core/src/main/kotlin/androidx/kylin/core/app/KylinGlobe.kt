package androidx.kylin.core.app

import android.app.Application

/**
 * 全局配置
 */
object KylinGlobe {

    private lateinit var application: Application

    /**
     * 内部获取应用程序，使用时请用调用[androidx.kylin.core.extension.app]方法进行获取。
     */
    internal fun getApp(): Application {
        return application
    }

    /**
     * 初始化全局应用程序
     */
    fun initialize(application: Application): Application {
        if (!::application.isInitialized || application != this.application) {
            this.application = application
        }
        return this.application
    }

}