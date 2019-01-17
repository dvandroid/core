package core.android.com.corelib.network.module

import core.android.com.corelib.inject.scopes.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

/**
 * This class is applicable to those interceptor doesn't use Oauth or Keys
 * as param in headers
 */

@Module
class NetworkInterceptorModule {

    @Provides
    @PerApplication
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url()
                    .newBuilder()
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

}