package core.android.com.corelib.mvp

interface BaseMvpPresenter<V : BaseMvpView, I : BaseMvpInteractor> {

    fun onAttachView(view: V?)

    fun onDetachView()

    fun getView(): V?

}