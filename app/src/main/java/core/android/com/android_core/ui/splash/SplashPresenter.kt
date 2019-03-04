package core.android.com.android_core.ui.splash

import android.util.Log
import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.corelib.helper.SchedulerProvider
import core.android.com.corelib.mvp.BaseMvpPresenterImpl
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : SplashContract.SplashMvpView, I : SplashContract.SplashMvpInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable)
    : BaseMvpPresenterImpl<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        SplashContract.SplashMvpPresenter<V, I> {

    override fun populateTbl() {
        interactor?.let {
            compositeDisposable.add(it.populateEmployeeTable()
                    .subscribeOn(schedulerProvider.getIOThreadScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
                    .subscribe {
                        getView()?.let {
                            getView()?.let { dataAdded() }
                        }
                    })
        }
    }

    private fun dataAdded() {
        getView().let { it!!.databasePopulated() }
    }

    override fun getUserName(): String {
        return interactor!!.getUserName()!!
    }

    override fun isUserLoggedIn(): Boolean {
        interactor.let { return it!!.isUserLoggedIn() }
    }


}