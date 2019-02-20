package core.android.com.corelib.module

import android.app.Application
import android.content.Context
import core.android.com.corelib.dialog.CustomDialog
import core.android.com.corelib.helper.DateUtils
import core.android.com.corelib.inject.qualifiers.ForActivity
import core.android.com.corelib.inject.qualifiers.ForApplication
import core.android.com.corelib.inject.scopes.PerActivity
import core.android.com.corelib.inject.scopes.PerApplication
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class BaseModule {


    @Provides
    @PerApplication
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun provideLogger(): Timber.Tree = Timber.DebugTree()

}