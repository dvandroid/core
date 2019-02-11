package core.android.com.corelib.helper

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UtilsTest{

    private lateinit var utils: Utils

    @Before
    fun setUp(){
        utils = Utils()
    }

    @Test
    fun shouldShowSuccessWithDoubleValue(){

        var value = utils.displayWithCommaAndDecimalPoint(1000.00)

        Assert.assertEquals("1,000.00", value)
    }

    @Test
    fun shouldShowSuccessWithIntValue(){

        val intValue = 1000

        var value = utils.displayWithCommaAndDecimalPoint(intValue.toDouble())

        Assert.assertEquals("1,000.00", value)
    }

    @Test
    fun shouldShowPrice(){

        var value = utils.displayPriceWithCurrency(1001.11555, Currency.Peso)

        Assert.assertEquals(utils.getCurrent(Currency.Peso) + "1,001.12", value)
    }

    @Test
    fun shouldShowSuccessWithComma(){

        var value = utils.displayWithComma(1000000)

        Assert.assertEquals("1,000,000", value)
    }
}