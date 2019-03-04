package core.android.com.android_core.di

import android.app.Application
import core.android.com.android_core.App
import core.android.com.android_core.di.modules.AppModule
import core.android.com.android_core.di.modules.NetworkModule
import core.android.com.android_core.di.modules.TimberModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), NetworkModule::class, TimberModule::class, (AppModule::class),
    (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}