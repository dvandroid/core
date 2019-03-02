package core.android.com.android_core.api.endpoints

import core.android.com.android_core.Login
import core.android.com.corelib.module.AccessToken
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FirstTestApi {

    @POST
    fun refreshToken(refreshToken: String): Single<AccessToken>

    @POST("https://rush-service.dev.api-rush.globedv.com/customer-service//customer/login/mobile")
    fun login(@Body loginData: Login): Single<String>

}