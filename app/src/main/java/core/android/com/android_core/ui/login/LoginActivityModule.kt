package core.android.com.android_core.ui.login

import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    internal fun provideLoginInteractor(loginInteractor: LoginInteractor): LoginContact.LoginMvpInteractor = loginInteractor

    @Provides
    internal fun provideLoginPresenter(loginPresenter: LoginPresenter<LoginContact.LoginMvpView, LoginContact.LoginMvpInteractor>)
            : LoginContact.LoginMvpPresenter<LoginContact.LoginMvpView, LoginContact.LoginMvpInteractor> = loginPresenter

}