package core.android.com.corelib.helper

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class Validation @Inject constructor() {

    /**
     * Function for email validation
     * @param email
     * @return true if valid, else false
     */
    fun validateEmail(email : String): Result{

        val emailPattern    : String    =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern         : Pattern   = Pattern.compile(emailPattern)
        val matcher         : Matcher   = pattern.matcher(email)

        when(matcher.matches()){
            false -> return Result(false, "Invalid email")
        }

        return Result()
    }

    /**
     * Function for mobile validation with prefix (+63)
     * @param mobile (e.g. 9123456789)
     * @return true if valid, else false
     */
    fun validateMobileWithPrefix(mobile: String): Result{

        when {
            mobile.length != 10   -> return Result(false, "Mobile no. should be 10 digits")
            mobile.take(1) != "9" -> return Result(false, "Mobile no. should start with 9")
        }

        return Result()
    }

    /**
     * Function for mobile validation
     * @param mobile (e.g. 09123456789)
     * @return true if valid, else false
     */
    fun validateMobile(mobile: String): Result{

        when{
            mobile.length != 11   -> return Result(false, "Mobile no. should be 11 digits")
            mobile.take(2) != "09" -> return Result(false, "Mobile no. should start with 09")
        }

        return Result()
    }
}