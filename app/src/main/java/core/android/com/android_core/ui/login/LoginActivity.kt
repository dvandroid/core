package core.android.com.android_core.ui.login

import android.content.Intent
import android.os.Bundle
import core.android.com.android_core.R
import core.android.com.android_core.ui.main.MainActivity
import core.android.com.corelib.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContact.LoginMvpView {

    @Inject
    lateinit var mPresenter: LoginContact.LoginMvpPresenter<LoginContact.LoginMvpView, LoginContact.LoginMvpInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter.onAttachView(this)

        setOnClickListeners()

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()
    }

    private fun setOnClickListeners() {
        btn_login.setOnClickListener { mPresenter.onServerLogin(et_username.text.toString(), et_password.text.toString()) }
    }


    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
