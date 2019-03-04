package core.android.com.corelib.mvp

interface BaseMvpInteractor {

    fun isUserLoggedIn(): Boolean

    fun performUserLogout()

}