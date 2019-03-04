package core.android.com.android_core.data.database.repo

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface EmployeesRepo {

    fun isEmployeeRepoEmpty(): Single<Boolean>?

    fun insertEmployees(employees: List<EmployeeObject>): Completable

    fun loadEmployees(): Maybe<List<EmployeeObject>>

    fun loadEmployeeById(id: String): Maybe<List<EmployeeObject>>


}