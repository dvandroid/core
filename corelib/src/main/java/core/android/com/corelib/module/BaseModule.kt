package core.android.com.corelib.module

import android.app.Application
import android.content.Context
import core.android.com.corelib.dialog.CustomDialog
import core.android.com.corelib.inject.qualifiers.ForActivity
import core.android.com.corelib.inject.qualifiers.ForApplication
import dagger.Module
import dagger.Provides

@Module
class BaseModule {

    @Provides
    @ForApplication
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @ForActivity
    fun provideDialog(context: Context): CustomDialog = CustomDialog(context)

}