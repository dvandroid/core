package core.android.com.android_core.ui.main

import android.content.Context
import core.android.com.android_core.data.api.network.ApiHelper
import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.android_core.data.database.repo.EmployeesRepo
import core.android.com.android_core.data.pref.PreferenceHelper
import core.android.com.android_core.ui.base.AppBaseInteractor
import io.reactivex.Maybe
import javax.inject.Inject

class MainActivityInteractor @Inject constructor(private val employeeTblHelper: EmployeesRepo, private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper)
    : AppBaseInteractor(preferenceHelper, apiHelper), MainActivityContract.MainActivityMvpInteractor {
    override fun getData(): Maybe<List<EmployeeObject>> {
        return employeeTblHelper.loadEmployees()
    }
}