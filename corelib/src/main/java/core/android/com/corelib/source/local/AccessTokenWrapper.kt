package core.android.com.corelib.source.local

import core.android.com.corelib.module.AccessToken
import javax.inject.Inject


class AccessTokenWrapper @Inject constructor(private val sharedPrefApi: SharedPrefModule) {
    private var accessToken: AccessToken? = null

    fun getAccessToken(): AccessToken? {
        if (accessToken == null) {
            accessToken = sharedPrefApi.getObject(SharedPrefModule.ACCESS_TOKEN, AccessToken::class.java)
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: AccessToken) {
        this.accessToken = accessToken
        sharedPrefApi.putObject(SharedPrefModule.ACCESS_TOKEN, accessToken)
    }
}