package core.android.com.android_core.data.pref

import android.content.Context
import android.content.SharedPreferences
import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.di.PreferenceInfo
import javax.inject.Inject


class AppPreferenceHelper @Inject constructor(context: Context,
                                              @PreferenceInfo private val prefName: String) : PreferenceHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    override fun getCurrentEmail(): String? = mPrefs.getString(PREF_KEY_USER_EMAIL, "")

    override fun setCurrentEmail(token: String?) = mPrefs.edit().putString(PREF_KEY_USER_EMAIL, token).apply()


    override fun getCurrentUserLoggedInMode(): Int = mPrefs.getInt(PREF_KEY_USER_LOGIN_MODE, AppConstants.LoggedInMode.LOGIN_MODE_LOGGED_OUT.type)

    override fun setCurrentUserLoggedInMode(mode: AppConstants.LoggedInMode) = mPrefs.edit().putInt(PREF_KEY_USER_LOGIN_MODE, mode.type).apply()

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_USER_ID, AppConstants.NULL_INDEX)
        return when (userId) {
            AppConstants.NULL_INDEX -> null
            else -> userId
        }
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_USER_ID, id).apply()
    }

    override fun getCurrentUserName(): String? = mPrefs.getString(PREF_KEY_USER_NAME, "")

    override fun setCurrentUserName(userName: String?) = mPrefs.edit().putString(PREF_KEY_USER_NAME, userName).apply()


    companion object {
        private val PREF_KEY_USER_LOGIN_MODE = "PREF_KEY_USER_LOGIN_MODE"
        private val PREF_KEY_USER_ID = "PREF_KEY_USER_ID"
        private val PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        private val PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME"
    }

}