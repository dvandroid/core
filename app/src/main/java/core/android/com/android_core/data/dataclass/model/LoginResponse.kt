package core.android.com.android_core.data.dataclass.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(@Expose
                         @SerializedName("name")
                          var name: String? = null,

                         @Expose
                         @SerializedName("email")
                          var email: String? = null,

                         @Expose
                         @SerializedName("id")
                          var id: Long? = null)