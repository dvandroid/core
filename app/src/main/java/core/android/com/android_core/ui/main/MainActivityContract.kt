package core.android.com.android_core.ui.main

import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.corelib.mvp.BaseMvpInteractor
import core.android.com.corelib.mvp.BaseMvpPresenter
import core.android.com.corelib.mvp.BaseMvpView
import io.reactivex.Maybe

object MainActivityContract {

    interface MainActivityMvpView : BaseMvpView {

        fun showList(list: List<EmployeeObject>)

    }

    interface MainActivityMvpInteractor : BaseMvpInteractor {

        fun getData(): Maybe<List<EmployeeObject>>

    }

    interface MainActivityMvpPresenter<V : MainActivityMvpView, I : MainActivityMvpInteractor> : BaseMvpPresenter<V, I> {

        fun getEmployeeList()
    }

}