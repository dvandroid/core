package core.android.com.android_core.data.database.repo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "employees")
data class EmployeeObject(

        @Expose
        @PrimaryKey
        @SerializedName("employee_id")
        @ColumnInfo(name = "employee_id")
        var optionText: String,

        @Expose
        @SerializedName("employee_name")
        @ColumnInfo(name = "employee_name")
        var questionId: String

)