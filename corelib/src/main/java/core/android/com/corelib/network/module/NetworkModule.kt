package core.android.com.corelib.network.module

import com.squareup.moshi.Moshi
import core.android.com.corelib.network.adapter.ApplicationJsonAdapterFactory
import core.android.com.corelib.inject.scopes.PerApplication
import dagger.Module
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.Provides
import okhttp3.Authenticator


@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @PerApplication
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @PerApplication
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Provides
    @PerApplication
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

}