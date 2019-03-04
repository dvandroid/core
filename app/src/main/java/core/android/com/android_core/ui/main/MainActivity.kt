package core.android.com.android_core.ui.main

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import core.android.com.android_core.R
import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.android_core.ui.adapters.EmployeeListAdapter
import core.android.com.corelib.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityContract.MainActivityMvpView {

    @Inject
    lateinit var mPresenter: MainActivityContract.SplashMvpPresenter<MainActivityContract.MainActivityMvpView, MainActivityContract.MainActivityMvpInteractor>


    @Inject
    internal lateinit var employeeListAdapter: EmployeeListAdapter

    override fun showList(list: List<EmployeeObject>) {
        employeeListAdapter.addBlogsToList(list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = DefaultItemAnimator()
        rv_list.adapter = employeeListAdapter

        mPresenter.onAttachView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetachView()
    }


}