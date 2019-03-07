package core.android.com.android_core.api

import core.android.com.android_core.BuildConfig.*
import core.android.com.corelib.BuildConfig
import core.android.com.android_core.api.endpoints.FirstTestApi
import core.android.com.android_core.api.endpoints.SecondTestApi
import core.android.com.corelib.inject.scopes.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Authenticator

@Module
class ApiModule {

    @Provides
    @PerApplication
    fun provideFirstTestApi(builder: Retrofit.Builder,
                            okHttpClientBuilder: OkHttpClient.Builder,
                            httpLoggingInterceptor: HttpLoggingInterceptor,
                            converterFactory: Converter.Factory
                            /*  authenticator: Authenticator,*/
            /* apiKeyInterceptor: Interceptor*/): FirstTestApi {

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        okHttpClientBuilder
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

        val client = okHttpClientBuilder.build()

        return builder.client(client)
                .baseUrl(FIRST_END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build()
                .create(FirstTestApi::class.java)
    }


    @Provides
    @PerApplication
    fun provideSecondTestApi(builder: Retrofit.Builder,
                             okHttpClientBuilder: OkHttpClient.Builder,
                             httpLoggingInterceptor: HttpLoggingInterceptor,
                             converterFactory: Converter.Factory
                             /* authenticator: Authenticator,*/
            /*  apiKeyInterceptor: Interceptor*/): SecondTestApi {

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        okHttpClientBuilder
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

        val client = okHttpClientBuilder.build()

        return builder.client(client)
                .baseUrl(SECOND_END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build()
                .create(SecondTestApi::class.java)
    }

}