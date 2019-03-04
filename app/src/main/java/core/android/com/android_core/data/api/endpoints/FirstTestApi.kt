package core.android.com.android_core.data.api.endpoints

import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import core.android.com.corelib.module.AccessToken
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FirstTestApi {

    @POST("http://www.mydotwidgets.com/api/login")
    fun login(@Body loginData: ApiRequest.ServerLoginRequest): Observable<LoginResponse>

}