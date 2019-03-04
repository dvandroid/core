package core.android.com.android_core.data.api.network

import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import io.reactivex.Observable

interface ApiHelper {

    fun performServerLogin(request: ApiRequest.ServerLoginRequest): Observable<LoginResponse>

    fun getUserList(request: ApiRequest.UserListrequest): List<Observable<LoginResponse>>

}