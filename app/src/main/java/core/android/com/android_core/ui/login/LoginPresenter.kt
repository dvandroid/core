package core.android.com.android_core.ui.login

import android.util.Log
import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import core.android.com.corelib.helper.SchedulerProvider
import core.android.com.corelib.mvp.BaseMvpPresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter<V : LoginContact.LoginMvpView, I : LoginContact.LoginMvpInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable)
    : BaseMvpPresenterImpl<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        LoginContact.LoginMvpPresenter<V, I> {

    override fun onServerLogin(username: String, password: String) {
        when {
            username.isEmpty() -> getView()?.showError(AppConstants.USERNAME_ERROR)
            password.isEmpty() -> getView()?.showError(AppConstants.PASSWORD_ERROR)
            else -> {

                getView()?.showProgress()
                var request = ApiRequest.ServerLoginRequest("2", "Q6Fkf7wg6ERd94aOqLLdfKzMlq93A1Mm6WvcaAFj", "password", username, password, "*")

                interactor?.let {
                    compositeDisposable.add(it.loginToServer(request)
                            ?.compose(schedulerProvider.ioToMainObservableScheduler())?.subscribe({ loginResponse ->
                                updateSharedPref(loginResponse = loginResponse,
                                        loggedInMode = AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER)
                                getView()?.openMainActivity()
                            }, { err -> println(err) }))
                }

            }
        }
    }

    private fun updateSharedPref(loginResponse: LoginResponse, loggedInMode: AppConstants.LoggedInMode) {
        interactor?.updateUserInSharedPref(loginResponse, loggedInMode)

    }
}