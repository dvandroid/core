package core.android.com.corelib.helper

import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class Utils @Inject constructor() {

    /**
     * Function for displaying value with comma/s and decimal point
     * @param   value
     * @return  display string
     */
    fun displayWithCommaAndDecimalPoint(value : Double): String{

        val decimalFormat = DecimalFormat("#,##0.00")
        decimalFormat.roundingMode = RoundingMode.HALF_UP
        return decimalFormat.format(value).toString()
    }

    /**
     * Function for display value with comma/s
     * @param value
     * @return display string
     */
    fun displayWithComma(value : Int): String{
        val decimalFormat = DecimalFormat("#,###")
        return  decimalFormat.format(value).toString()
    }

    /**
     * Function for displaying price with currency
     * @param   price
     * @param   currency
     * @return  display string
     */
    fun displayPriceWithCurrency(price : Double, currency : Currency) : String{
        return getCurrent(currency) + displayWithCommaAndDecimalPoint(price)
    }

    /**
     * Function for getting current
     * @param  currency type
     * @return display string
     */
    fun getCurrent(currency: Currency): String{
        return when(currency){
            Currency.Php -> "Php"
            Currency.Peso-> "₱"
            Currency.Dollar-> "$"
        }
    }
}