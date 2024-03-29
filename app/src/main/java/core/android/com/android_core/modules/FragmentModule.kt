package core.android.com.android_core.modules

import core.android.com.android_core.modules.login.LoginFragment
import core.android.com.corelib.inject.scopes.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

}