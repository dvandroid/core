package core.android.com.android_core.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.api.network.ApiHelper
import core.android.com.android_core.data.api.network.AppApiHelper
import core.android.com.android_core.data.database.AppDatabase
import core.android.com.android_core.data.database.repo.EmployeesRepo
import core.android.com.android_core.data.database.repo.EmployeesRepository
import core.android.com.android_core.data.pref.AppPreferenceHelper
import core.android.com.android_core.data.pref.PreferenceHelper
import core.android.com.android_core.di.PreferenceInfo
import core.android.com.corelib.helper.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME).build()

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper


    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    internal fun provideEmployeeRepoHelper(appDatabase: AppDatabase): EmployeesRepo = EmployeesRepository(appDatabase.employeesDao())

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


}