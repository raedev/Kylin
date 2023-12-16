package androidx.kylin.core.internal

import android.app.Application
import android.content.Context

internal object GlobeApplication {
    // application from provider
    lateinit var application: Application
        private set

    internal fun init(context: Context) {
        application = context as Application
    }
}