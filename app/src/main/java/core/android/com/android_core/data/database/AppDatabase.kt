package core.android.com.android_core.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import core.android.com.android_core.data.database.repo.EmployeeObject
import core.android.com.android_core.data.database.repo.EmployeesDao

@Database(entities = [(EmployeeObject::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeesDao
}