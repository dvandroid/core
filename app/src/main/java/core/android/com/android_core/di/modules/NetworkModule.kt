package core.android.com.android_core.di.modules

import com.squareup.moshi.Moshi
import core.android.com.android_core.data.api.endpoints.FirstTestApi
import core.android.com.android_core.data.api.endpoints.SecondTestApi
import core.android.com.corelib.BuildConfig
import core.android.com.corelib.network.adapter.ApplicationJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Accept-Language", "en")
                    .addHeader("Content-Type", "application/json")
                    .build())
        }
    }

    @Provides
    @Singleton
    fun provideFirstTestApi(builder: Retrofit.Builder,
                            okHttpClientBuilder: OkHttpClient.Builder,
                            httpLoggingInterceptor: HttpLoggingInterceptor,
                            converterFactory: Converter.Factory,
                            header: Interceptor
            /*  authenticator: Authenticator,*/
            /* apiKeyInterceptor: Interceptor*/): FirstTestApi {

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(header)
        okHttpClientBuilder
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

        val client = okHttpClientBuilder.build()

        return builder.client(client)
                .baseUrl(core.android.com.android_core.BuildConfig.FIRST_END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build()
                .create(FirstTestApi::class.java)
    }


    @Provides
    @Singleton
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
                .baseUrl(core.android.com.android_core.BuildConfig.SECOND_END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

//                .header("Authorization", "Bearer $it"
                .addConverterFactory(converterFactory)

                .build()
                .create(SecondTestApi::class.java)
    }

}