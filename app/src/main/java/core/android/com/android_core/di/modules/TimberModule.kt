package core.android.com.android_core.di.modules

import android.util.Log
import core.android.com.corelib.BuildConfig
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
object TimberModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideTimberTree(): Timber.Tree =
            object : Timber.DebugTree() {
                override fun isLoggable(tag: String?, priority: Int) =
                        BuildConfig.DEBUG || priority >= Log.INFO
            }
}