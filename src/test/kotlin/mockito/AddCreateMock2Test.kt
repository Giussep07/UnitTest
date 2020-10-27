package mockito

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AddCreateMock2Test {

    @InjectMocks
    private val add: Add? = null

    @Mock
    private val validNumber: ValidNumber? = null

    @Mock
    private val print: Print? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest() {
        add?.add(3, 2)
        Mockito.verify(validNumber)?.checkNumber(3)
        //Mockito.verify(validNumber)?.checkNumber(2)
    }
}