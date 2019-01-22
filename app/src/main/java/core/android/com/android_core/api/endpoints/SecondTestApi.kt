package core.android.com.android_core.api.endpoints

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface SecondTestApi {

    @POST("https://rush-service.dev.api-rush.globedv.com//merchant-service/token/customer-app")
    fun getMerchantToken(@Field("secret") secretKey: String = "P6CWWjrDC2w6lXXywjFV5Lrvln1Zh5fK",
                         @Field("key") key: String = "lSulSYfbi9yH")

}