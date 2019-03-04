package core.android.com.android_core.ui.login

import android.content.Context
import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.api.network.ApiHelper
import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import core.android.com.android_core.data.pref.PreferenceHelper
import core.android.com.android_core.ui.base.AppBaseInteractor
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper)
    : AppBaseInteractor(preferenceHelper, apiHelper), LoginContact.LoginMvpInteractor {

    override fun loginToServer(request: ApiRequest.ServerLoginRequest) = apiHelper.performServerLogin(request)

    override fun updateUserInSharedPref(loginResponse: LoginResponse, loggedInMode: AppConstants.LoggedInMode) {
        preferenceHelper.let {
            it.setCurrentUserId(loginResponse.id)
            it.setCurrentUserName(loginResponse.name)
            it.setCurrentEmail(loginResponse.email)
            it.setCurrentUserLoggedInMode(loggedInMode)
        }
    }
}