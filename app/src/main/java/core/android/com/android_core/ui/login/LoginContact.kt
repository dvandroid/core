package core.android.com.android_core.ui.login

import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.api.network.ResultAsyncState
import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import core.android.com.corelib.mvp.BaseMvpInteractor
import core.android.com.corelib.mvp.BaseMvpPresenter
import core.android.com.corelib.mvp.BaseMvpView
import io.reactivex.Observable

object LoginContact {

    interface LoginMvpView : BaseMvpView {
        fun openMainActivity()
    }

    interface LoginMvpInteractor : BaseMvpInteractor {
        fun loginToServer(request: ApiRequest.ServerLoginRequest): Observable<LoginResponse>?
        fun updateUserInSharedPref(loginResponse: LoginResponse, loggedInMode: AppConstants.LoggedInMode)
    }

    interface LoginMvpPresenter<V : LoginMvpView, I : LoginMvpInteractor> : BaseMvpPresenter<V, I> {
        fun onServerLogin(username: String, password: String)
    }
}