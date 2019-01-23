package core.android.com.android_core.network

import android.content.Context
import com.google.gson.Gson
import core.android.com.corelib.source.local.AccessTokenWrapper
import core.android.com.corelib.source.local.SharedPrefModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TokenModule {

    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context, gson: Gson): SharedPrefModule {
        return SharedPrefModule(context, gson)
    }

    @Singleton
    @Provides
    fun provideAccessTokenWrapper(sharedPrefApi: SharedPrefModule): AccessTokenWrapper {
        return AccessTokenWrapper(sharedPrefApi)
    }

}