package core.android.com.android_core.data.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiRequest {

    data class ServerLoginRequest internal constructor(@Expose
                                                       @SerializedName("client_id") internal val client_id: String,
                                                       @Expose
                                                       @SerializedName("client_secret") internal val client_secret: String,
                                                       @Expose
                                                       @SerializedName("grant_type") internal val grant_type: String,
                                                       @Expose
                                                       @SerializedName("username")
                                                       internal val username: String,
                                                       @Expose
                                                       @SerializedName("password")
                                                       internal val password: String,
                                                       @Expose
                                                       @SerializedName("scope")
                                                       internal val scope: String
    )


    data class UserListrequest internal constructor(@Expose
                                                    @SerializedName("email") internal val email: String,
                                                    @Expose
                                                    @SerializedName("password") internal val password: String)
}