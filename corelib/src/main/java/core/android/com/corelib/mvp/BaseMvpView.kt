package core.android.com.corelib.mvp

import android.content.Context
import android.support.annotation.StringRes

interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showMessage(message: String)

    fun showProgress()

    fun hideProgress()

}