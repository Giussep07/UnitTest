package mockito

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.ArithmeticException

internal class ValidNumberTest {

    private var validNumber: ValidNumber? = null

    @BeforeEach
    fun setUp() {
        validNumber = ValidNumber()
    }

    @AfterEach
    fun tearDown() {
        validNumber = null
    }

    @Test
    fun checkTest() {
        assertEquals(true, validNumber?.checkNumber(5))
    }

    @Test
    fun checkNegativeTest() {
        assertEquals(false, validNumber?.checkNumber(-7))
    }

    @Test
    fun checkStringTest() {
        assertEquals(false, validNumber?.checkNumber("hello"))
    }

    @Test
    fun checkDoubleTest() {
        assertEquals(false, validNumber?.checkNumber(5.5))
    }

    @Test
    fun checkZeroTest() {
        assertEquals(true, validNumber?.checkZero(-57))
    }

    @Test
    fun checkZeroStringTest() {
        assertEquals(false, validNumber?.checkZero("5"))
    }

    @Test
    fun checkZero0Test() {
        assertThrows(ArithmeticException::class.java) {
            validNumber?.checkZero(0)
        }
    }

    @Test
    fun doubleToIntTest() {
        assertEquals(9, validNumber?.doubleToInt(9.999))
    }

    @Test
    fun doubleToIntErrorTest() {
        assertEquals(0, validNumber?.doubleToInt("9.999"))
    }


}