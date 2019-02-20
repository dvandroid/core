package core.android.com.android_core.modules

import android.content.Context
import core.android.com.android_core.MainActivity
import core.android.com.corelib.inject.qualifiers.ForActivity
import core.android.com.corelib.inject.scopes.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {

    @Binds
    @PerActivity
    abstract fun provideContext(activity: MainActivity): MainActivity


}