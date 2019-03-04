package core.android.com.android_core.data.database.repo

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface EmployeesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<EmployeeObject>)

    @Query("SELECT * FROM employees WHERE employee_id = :employeeId")
    fun loadEmployeesById(employeeId: String): Maybe<List<EmployeeObject>>

    @Query("SELECT * FROM employees")
    fun loadAll(): Maybe<List<EmployeeObject>>
}