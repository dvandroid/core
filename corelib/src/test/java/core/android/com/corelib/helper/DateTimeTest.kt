package core.android.com.corelib.helper

import org.junit.Assert
import org.junit.Test

import org.junit.Before

class DateTimeTest {

    private lateinit var dateTime : DateTime

    @Before
    fun setUp(){
        dateTime = DateTime()
    }

    @Test
    fun shouldShowErrorWithValidDateInvalidFormat() {

        var date = dateTime.formatDate("1989-07-30", "xxx")

        Assert.assertNull(date)
    }

    @Test
    fun showShowErrorWithInvalidDateValidFormat(){

        var date = dateTime.formatDate("07-30-1989", "MMMM dd, yyyy")

        Assert.assertNull(date)
    }

    @Test
    fun showShowErrorWithInvalidDateInvalidFormat(){

        var date = dateTime.formatDate("07-30-1989", "xxx")

        Assert.assertNull(date)
    }

    @Test
    fun shouldShowSuccessWithValidDateValidFormat(){

        var date = dateTime.formatDate("1989-07-30", "MMMM dd, yyyy")

        Assert.assertNotNull(date)
        Assert.assertEquals("July 30, 1989", date)
    }

    @Test
    fun shouldShowErrorWithInvalidMilitaryTime(){

        var time = dateTime.convertMilitaryToStandardTime("25:00:00")

        Assert.assertNull(time)
    }

    @Test
    fun shouldShowErrorWithInvalidMilitaryTimeFormat(){

        var time = dateTime.convertMilitaryToStandardTime("23:00")

        Assert.assertNull(time)
    }

    @Test
    fun shouldShowSuccessWithValidMilitaryTime(){

        var time = dateTime.convertMilitaryToStandardTime("23:00:00")

        Assert.assertNotNull(time)
        Assert.assertEquals("11:00 PM", time)
    }

    @Test
    fun shouldShowErrorWithInvalidStandardTime(){

        var time = dateTime.convertStandardToMilitaryTime("12:00")

        Assert.assertNull(time)
    }

    @Test
    fun shouldShowErrorWithInvalidStandardTimeFormat(){

        var time = dateTime.convertStandardToMilitaryTime("23:00 AM")

        Assert.assertNull(time)
    }

    @Test
    fun shouldShowSuccessWithValidStandardTime(){

        var time = dateTime.convertStandardToMilitaryTime("11:00 PM")

        Assert.assertNotNull(time)
        Assert.assertEquals("23:00:00", time)
    }

}