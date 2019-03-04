package core.android.com.android_core.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import core.android.com.android_core.R
import core.android.com.android_core.ui.login.LoginActivity
import core.android.com.android_core.ui.main.MainActivity
import core.android.com.corelib.mvp.BaseActivity
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity(), SplashContract.SplashMvpView {

    @Inject
    lateinit var mPresenter: SplashContract.SplashMvpPresenter<SplashContract.SplashMvpView, SplashContract.SplashMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mPresenter.onAttachView(this)

        checkUser()

        mPresenter.populateTbl()

    }

    override fun databasePopulated() {
        Toast.makeText(this, "Database populated", Toast.LENGTH_SHORT).show()
    }


    private fun checkUser() {
        if (mPresenter.isUserLoggedIn()) greetings() else openLoginActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()
    }

    override fun greetings() {
        mPresenter.getUserName()

        Timer("SettingUp", false).schedule(500) {
            openMainActivity()
        }
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}