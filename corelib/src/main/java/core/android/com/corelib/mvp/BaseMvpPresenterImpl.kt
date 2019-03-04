package core.android.com.corelib.mvp

import core.android.com.corelib.helper.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseMvpPresenterImpl<V : BaseMvpView, I : BaseMvpInteractor>
(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider,
                     protected val compositeDisposable: CompositeDisposable)
    : BaseMvpPresenter<V, I> {

    protected var mView: V? = null

    private var view: V? = null

    private val isViewAttached: Boolean get() = view != null

    override fun onAttachView(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetachView() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }


}