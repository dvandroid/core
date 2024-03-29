package core.android.com.corelib.helper

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class Validation @Inject constructor() {

    /**
     * Function for email validation
     * @param email
     * @return Result : true if valid, false if not and error message
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
     * @return Result : true if valid, false if not and error message
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
     * @return Result : true if valid, false if not and error message
     */
    fun validateMobile(mobile: String): Result{

        when{
            mobile.length != 11   -> return Result(false, "Mobile no. should be 11 digits")
            mobile.take(2) != "09" -> return Result(false, "Mobile no. should start with 09")
        }

        return Result()
    }

    /**
     * Function for validating 4-digit PIN
     * @param   PIN
     * @return  Result : true if valid, false if not and error message
     */
    fun validatePin(pin: String): Result{

        if(pin?.length < 4)
            return Result(false, "Please enter 4-digit PIN")

        return Result()
    }

    /**
     * Function for validating 6-digit PIN
     * @param   Otp
     * @return  Result : true if valid, false if not and error message
     */
    fun validateOtp(otp : String): Result{

        if(otp?.length < 6)
            return Result(false, "Please enter 6-digit OTP")

        return Result()

        return Result()
    }

}