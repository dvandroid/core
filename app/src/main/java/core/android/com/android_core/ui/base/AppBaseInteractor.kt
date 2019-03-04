package core.android.com.android_core.ui.base

import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.api.network.ApiHelper
import core.android.com.android_core.data.pref.PreferenceHelper
import core.android.com.corelib.mvp.BaseMvpInteractor

open class AppBaseInteractor() : BaseMvpInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper
    lateinit var apiHelper: ApiHelper

    constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn(): Boolean = this.preferenceHelper.getCurrentUserLoggedInMode() != AppConstants.LoggedInMode.LOGIN_MODE_LOGGED_OUT.type

    override fun performUserLogout() = preferenceHelper.let {
        it.setCurrentUserId(null)
        it.setCurrentEmail(null)
        it.setCurrentUserName(null)
        it.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGIN_MODE_LOGGED_OUT)
    }

}