package mockito

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class AddCreateMock1Test {

    private var add: Add? = null
    private var validNumber: ValidNumber? = null

    @BeforeEach
    fun setUp() {
        validNumber = Mockito.mock(ValidNumber::class.java)
        add = Add(validNumber!!)
    }

    @Test
    fun addTest() {
        add?.add(3, 2)
        Mockito.verify(validNumber)?.checkNumber(3)
        Mockito.verify(validNumber)?.checkNumber(2)
    }
}