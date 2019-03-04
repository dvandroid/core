package core.android.com.android_core.ui.main

import core.android.com.corelib.helper.SchedulerProvider
import core.android.com.corelib.mvp.BaseMvpPresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityPresenter<V : MainActivityContract.MainActivityMvpView, I : MainActivityContract.MainActivityMvpInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable)
    : BaseMvpPresenterImpl<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        MainActivityContract.MainActivityMvpPresenter<V, I> {

    override fun getEmployeeList() {
        interactor?.let {
            compositeDisposable.add(it.getData()
                    .subscribeOn(schedulerProvider.getIOThreadScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
                    .subscribe {
                        getView()!!.showList(it)

                    })
        }
    }
}