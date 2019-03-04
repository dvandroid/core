package core.android.com.android_core.di

import core.android.com.android_core.ui.login.LoginActivity
import core.android.com.android_core.ui.login.LoginActivityModule
import core.android.com.android_core.ui.main.MainActivity
import core.android.com.android_core.ui.main.MainActivityModule
import core.android.com.android_core.ui.splash.SplashActivity
import core.android.com.android_core.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}