package core.android.com.corelib.helper

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.NonNull
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * Created by Mark Villaflor on 4/18/2018.
 */

open class DateUtils @Inject constructor(val context: Context) {

    /**
     * Formats the passed date string following the passed format pattern.
     * @param dateString    the string of the date to format
     * @param formatPattern the pattern to format date string
     */

    @NonNull
    fun formatDateString(@NonNull dateString: String, @NonNull formatPattern: String): String {
        val sdf = SimpleDateFormat(formatPattern, Locale.ENGLISH)
        val date = parseStringToDateWithPassedPattern(dateString, "yyyy-MM-dd")
        return sdf.format(date)
    }

    /**
     * Parses the passed string to a date with the passed format.
     * @param dateString    the string of the date to parse
     * @param formatPattern the pattern to format date string
     * @throws RuntimeException if there is any problems while parsing
     */

    @NonNull
    private fun parseStringToDateWithPassedPattern(@NonNull dateString: String, @NonNull formatPattern: String): Date {
        val sdf = SimpleDateFormat(formatPattern, Locale.GERMAN)
        try {
            return sdf.parse(dateString)
        } catch (e: ParseException) {
            throw RuntimeException("Error formatting $dateString with pattern $formatPattern to Date")
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun timeAgo(s: String): String {

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        // use SimpleDateFormat to define how to PARSE the INPUT
        val date = sdf.parse(s)

        return timeAgo(date.time.toString())
    }

}