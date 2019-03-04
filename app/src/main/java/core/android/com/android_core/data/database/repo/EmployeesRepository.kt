package core.android.com.android_core.data.database.repo

import io.reactivex.*
import io.reactivex.functions.Action
import javax.inject.Inject

class EmployeesRepository @Inject constructor(private val employeeDao: EmployeesDao) : EmployeesRepo {


    override fun isEmployeeRepoEmpty(): Single<Boolean>? = employeeDao.loadAll().isEmpty()

    override fun insertEmployees(employees: List<EmployeeObject>): Completable {
        return Completable.fromAction { employeeDao.insertAll(employees) }
    }

    override fun loadEmployees(): Maybe<List<EmployeeObject>> =  employeeDao.loadAll()

    override fun loadEmployeeById(id: String): Maybe<List<EmployeeObject>> = employeeDao.loadEmployeesById(id)
}