package core.android.com.android_core.modules.login

import core.android.com.android_core.Login

interface LoginContract {

    interface Presenter {
        fun doLogin(login: Login)
    }

    interface View {
        fun loadView()
    }

}
