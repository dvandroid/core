package core.android.com.corelib.module

import android.app.Application
import android.content.Context
import core.android.com.corelib.inject.qualifiers.ForApplication
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

    @Provides
    @ForApplication
    fun provideContext(application: Application): Context = application.applicationContext


}