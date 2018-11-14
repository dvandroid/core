package core.android.com.corelib.helper

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ValidationTest {

    private lateinit var validation : Validation

    @Before
    fun setUp(){
        validation = Validation()
    }

    @Test
    fun shouldShowValidEmail() {

        val result : Result = validation.validateEmail("test@yondu.com")

        Assert.assertNotNull(result)
        Assert.assertTrue(result.status)
    }

    @Test
    fun shouldShowInvalidEmail(){

        val result : Result = validation.validateEmail("test")

        Assert.assertNotNull(result)
        Assert.assertFalse(result.message, result.status)
    }

    @Test
    fun shouldShowValidMobileWithPrefix(){

        val result : Result = validation.validateMobileWithPrefix("9123456789")

        Assert.assertNotNull(result)
        Assert.assertTrue(result.status)
    }

    @Test
    fun shouldShowIncompleteMobileWithPrefix(){

        val result : Result = validation.validateMobileWithPrefix("912345678")

        Assert.assertNotNull(result)
        Assert.assertFalse(result.message, result.status)
    }

    @Test
    fun shouldShowInvalidMobileWithPrefix(){

        val result : Result = validation.validateMobileWithPrefix("0123456789")

        Assert.assertNotNull(result)
        Assert.assertFalse(result.message, result.status)
    }

    @Test
    fun shouldShowValidMobile(){

        val result : Result = validation.validateMobile("09123456789")

        Assert.assertNotNull(result)
        Assert.assertTrue(result.status)
    }

    @Test
    fun shouldShowIncompleteMobile(){

        val result : Result = validation.validateMobile("9123456789")

        Assert.assertNotNull(result)
        Assert.assertFalse(result.message, result.status)
    }

    @Test
    fun shouldShowInvalidMobile(){

        val result : Result = validation.validateMobile("08123456789")

        Assert.assertNotNull(result)
        Assert.assertFalse(result.message, result.status)
    }
}