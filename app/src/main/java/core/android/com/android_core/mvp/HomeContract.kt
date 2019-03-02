package core.android.com.android_core.mvp

import core.android.com.android_core.model.MenuObjects
import core.android.com.corelib.mvp.BaseMvpPresenter
import core.android.com.corelib.mvp.BaseMvpView

object HomeContract {

    interface View : BaseMvpView {
        fun showMenu(menuObjects: MenuObjects)

    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadMenu()

    }

}