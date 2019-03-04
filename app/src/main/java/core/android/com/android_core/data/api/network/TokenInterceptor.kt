package core.android.com.android_core.data.api.network

import core.android.com.corelib.inject.scopes.PerApplication
import core.android.com.corelib.source.local.AccessTokenWrapper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

/**
 * This class is applicable to those interceptor doesn't use Oauth or Keys
 * as param in headers
 */

@Module
class TokenInterceptor {

    @Provides
    @PerApplication
    fun provideApiKeyInterceptor(accessTokenWrapper: AccessTokenWrapper): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            request.newBuilder()
                    .addHeader("Authorization", accessTokenWrapper.getAccessToken()!!.token)
                    .header("Accept", "application/json")
            chain.proceed(request)
        }
    }

}