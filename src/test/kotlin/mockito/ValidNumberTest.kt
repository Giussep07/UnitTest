package mockito

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
}