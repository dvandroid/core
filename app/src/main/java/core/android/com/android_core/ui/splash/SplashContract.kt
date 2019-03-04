package core.android.com.android_core.ui.splash

import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.corelib.mvp.BaseMvpInteractor
import core.android.com.corelib.mvp.BaseMvpPresenter
import core.android.com.corelib.mvp.BaseMvpView
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

object SplashContract {
    interface SplashMvpView : BaseMvpView {
        fun greetings()

        fun openMainActivity()

        fun openLoginActivity()

        fun databasePopulated()
    }

    interface SplashMvpInteractor : BaseMvpInteractor {
        fun getUserName(): String?

        fun populateEmployeeTable(): Completable

    }

    interface SplashMvpPresenter<V : SplashMvpView, I : SplashMvpInteractor> : BaseMvpPresenter<V, I> {
        fun isUserLoggedIn(): Boolean

        fun getUserName(): String?

        fun populateTbl()
    }
}