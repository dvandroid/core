package core.android.com.corelib.mvp

import core.android.com.corelib.helper.SchedulerProvider
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    lateinit var mSchedulerProvider: SchedulerProvider
    lateinit var mCompositeDisposable: CompositeDisposable


    fun getSchedulerProvider(): SchedulerProvider {
        return mSchedulerProvider
    }


    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    protected fun <T> applyBinding(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe { disposable -> bindToLifecycle(disposable) }
        }
    }

    private fun bindToLifecycle(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }
}