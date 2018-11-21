package core.android.com.android_core.component

import core.android.com.android_core.App
import core.android.com.android_core.api.ApiModule
import core.android.com.corelib.db.RoomModule
import core.android.com.corelib.inject.scopes.PerApplication
import core.android.com.corelib.network.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Initializing modules for dependency
 */
@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    RoomModule::class,
    NetworkModule::class,
    ApiModule::class
])

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}