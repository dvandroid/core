package core.android.com.android_core.api.endpoints

import core.android.com.corelib.module.AccessToken
import io.reactivex.Single
import retrofit2.http.POST

interface FirstTestApi {

    @POST
    fun refreshToken(refreshToken: String): Single<AccessToken>


}