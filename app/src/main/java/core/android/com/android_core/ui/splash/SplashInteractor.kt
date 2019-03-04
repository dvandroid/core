package core.android.com.android_core.ui.splash

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import core.android.com.android_core.data.AppConstants
import core.android.com.android_core.data.api.network.ApiHelper
import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.android_core.data.database.repo.EmployeesRepo
import core.android.com.android_core.data.pref.PreferenceHelper
import core.android.com.android_core.ui.base.AppBaseInteractor
import core.android.com.android_core.utils.FileUtils
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

class SplashInteractor @Inject constructor(private val employeeTblHelper: EmployeesRepo, private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper)
    : AppBaseInteractor(preferenceHelper, apiHelper), SplashContract.SplashMvpInteractor {

    override fun populateEmployeeTable(): Completable {
        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()
        val type = `$Gson$Types`.newParameterizedTypeWithOwner(null, List::class.java, EmployeeObject::class.java)
        val employeeList = gson.fromJson<List<EmployeeObject>>(
                FileUtils.loadJSONFromAsset(
                        mContext,
                        AppConstants.DB_DATA),
                type)
        return employeeTblHelper.insertEmployees(employeeList)


    }

    override fun getUserName() = preferenceHelper.getCurrentUserName()
}