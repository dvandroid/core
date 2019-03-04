package core.android.com.android_core.ui.splash

import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashContract.SplashMvpInteractor = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashContract.SplashMvpView, SplashContract.SplashMvpInteractor>)
            : SplashContract.SplashMvpPresenter<SplashContract.SplashMvpView, SplashContract.SplashMvpInteractor> = splashPresenter
}