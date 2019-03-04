package core.android.com.android_core

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import core.android.com.android_core.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    protected lateinit var timberTree: Timber.Tree

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
        Stetho.initializeWithDefaults(this)
        Timber.plant(timberTree)
    }

}