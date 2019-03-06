package core.android.com.android_core

import core.android.com.android_core.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

open class App : DaggerApplication() {

    @Inject
    lateinit var loggingTree: Timber.Tree
    
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        initTimber()

      //  e("${dateUtils.timeAgo("10002")}")
    }


    private fun initTimber() {
        Timber.plant(loggingTree)
    }


}