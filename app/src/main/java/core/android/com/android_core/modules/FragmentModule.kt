package core.android.com.android_core.modules

import core.android.com.android_core.DashboardFragment
import core.android.com.android_core.RewardListFragment
import core.android.com.android_core.SplashFragment
import core.android.com.android_core.modules.login.LoginFragment
import core.android.com.corelib.inject.scopes.PerFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

}