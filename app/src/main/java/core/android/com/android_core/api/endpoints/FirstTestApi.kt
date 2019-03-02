package core.android.com.android_core.api.endpoints

import core.android.com.android_core.model.MenuObjects
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET

interface FirstTestApi {
    @GET("url")
    fun getRepository(
            @Field("field") name: String): Observable<MenuObjects>
}