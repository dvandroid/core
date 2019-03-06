package core.android.com.android_core.network

import android.util.Log
import core.android.com.android_core.api.endpoints.FirstTestApi
import core.android.com.corelib.inject.scopes.PerApplication
import core.android.com.corelib.source.local.AccessTokenWrapper
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator

@Module
class TokenAuthenticator {

  /*  @Provides
    @PerApplication
    fun provideAuthenticator( accessTokenWrapper: AccessTokenWrapper): Authenticator {
        return Authenticator { route, response ->

            val newAccessToken = firstService.refreshToken(accessTokenWrapper.getAccessToken()!!.refreshToken).blockingGet()
            accessTokenWrapper.saveAccessToken(newAccessToken)
            Log.i("TAG", "authenticator")
            response.request().newBuilder()
                    .header("Authorization", newAccessToken.token)
                    .build()
        }
    }*/
}