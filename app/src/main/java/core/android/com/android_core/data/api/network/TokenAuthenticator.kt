package core.android.com.android_core.data.api.network

import dagger.Module

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