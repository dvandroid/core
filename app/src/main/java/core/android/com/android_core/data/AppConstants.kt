package core.android.com.android_core.data

object AppConstants {

    internal val DB_NAME = "sample.db"
    internal val PREF_NAME = "sample_pref"
    internal val DB_DATA = "employees.json"

    internal val NULL_INDEX = -1L
    internal val USERNAME_ERROR = "Username is required."
    internal val PASSWORD_ERROR = "Password is required."


    enum class LoggedInMode constructor(val type: Int) {
        LOGIN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1),
        LOG_IN_MODE_FB(2),
    }

}