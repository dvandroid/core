package core.android.com.corelib.helper

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTime @Inject constructor() {

    /**
     * Function for formatting date
     * @param   date  : raw date
     * @param   format: desired format of raw date
     * @return  null if date/format is invalid
     *          newDate
     *
     */
    fun formatDate(date : String, format : String): String?{

        var newDate : String? = null

        try{

            val rawDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) //default date format from API
            rawDateFormat.isLenient = false //throw exception when the given input string is not in specified format

            val newDateFormat = SimpleDateFormat(format, Locale.getDefault())
            newDateFormat.isLenient = false

            newDate = newDateFormat.format(rawDateFormat.parse(date))

        }catch (e : Exception){
            e.printStackTrace()
        }

        return newDate
    }

    /**
     * Function for converting military time to standard time
     * @param   militaryTime (HH:mm:ss)
     * @return  null if time is invalid
     *          standardTime (hh:mm aa)
     */
    fun convertMilitaryToStandardTime(militaryTime : String): String?{

        var standardTime : String? = null

        try{

            val militaryTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            militaryTimeFormat.isLenient = false

            val standardTimeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault())

            standardTime = standardTimeFormat.format(militaryTimeFormat.parse(militaryTime))

        }catch (e : Exception){
            e.printStackTrace()
        }

        return standardTime
    }

    /**
     * Function for converting standard time to military time
     * @param   standardTime (hh:mm aa)
     * @return  null if time is invalid
     *          militaryTime (HH:mm:ss)
     */
    fun convertStandardToMilitaryTime(standardTime : String): String?{

        var militaryTime: String? = null

        try{

            val standardTimeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault())
            standardTimeFormat.isLenient = false

            val militaryTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

            militaryTime = militaryTimeFormat.format(standardTimeFormat.parse(standardTime))

        }catch (e : Exception){
            e.printStackTrace()
        }

        return militaryTime
    }

}