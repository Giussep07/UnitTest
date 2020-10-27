package mockito

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.stubbing.Answer
import org.mockito.Mockito.`when`
import kotlin.ArithmeticException

internal class AddTest {

    @InjectMocks
    private val add: Add? = null

    @Mock
    private val validNumber: ValidNumber? = null

    @Mock
    private val print: Print? = null

    @Captor
    private val captor: ArgumentCaptor<Int>? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addTest() {
        `when`(validNumber!!.checkNumber(3)).thenReturn(true)
        val checkNumber = validNumber.checkNumber(3)
        assertEquals(true, checkNumber)

        `when`(validNumber.checkNumber(-3)).thenReturn(false)
        val checkNumber2 = validNumber.checkNumber(-3)
        assertEquals(false, checkNumber2)
    }

    @Test
    fun addMockExceptionTest() {
        `when`(validNumber?.checkZero(0)).thenThrow(ArithmeticException("Can't accept zero"))

        var exception: Exception? = null
        try {
            validNumber?.checkZero(0)
        } catch (e: ArithmeticException) {
            exception = e
        }

        assertNotNull(exception)
        assertThrows(ArithmeticException::class.java) {
            validNumber?.checkZero(0)
        }
    }

    @Test
    fun addRealMethodTest() {
        `when`(validNumber?.checkNumber(3)).thenCallRealMethod()
        assertEquals(true, validNumber?.checkNumber(3))

        `when`(validNumber?.checkNumber("3")).thenCallRealMethod()
        assertEquals(false, validNumber?.checkNumber("3"))
    }

    @Test
    fun addDoubleToIntThenAnswerTest() {
        val answer: Answer<Int> = Answer { 7 }
        `when`(validNumber?.doubleToInt(7.777)).thenAnswer(answer)
        assertEquals(14, add?.addInt(7.777))
    }

    @Test
    fun aaaPatternTest() {
        //Arrange
        `when`(validNumber?.checkNumber(4)).thenReturn(true)
        `when`(validNumber?.checkNumber(5)).thenReturn(true)
        //Act
        val result = add?.add(4, 5)
        //Assert
        assertEquals(9, result)
    }

    @Test
    fun bddPatternTest() {
        //Given
        given(validNumber?.checkNumber(4)).willReturn(true)
        given(validNumber?.checkNumber(5)).willReturn(true)
        //When
        val result = add?.add(4, 5)
        //Then
        assertEquals(9, result)
    }

    @Test
    fun argumentMatcherTest() {
        //Given
        given(validNumber?.checkNumber(ArgumentMatchers.anyInt())).willReturn(true)
        //When
        val result = add?.add(4, 5)
        //Then
        assertEquals(9, result)
    }

    @Test
    fun addPrintTest() {
        //Given
        given(validNumber?.checkNumber(4)).willReturn(true)
        given(validNumber?.checkNumber(5)).willReturn(true)
        //When
        add!!.addPrint(4, 5)
        //Then
        //Mockito.verify(validNumber, Mockito.times(2))?.checkNumber(4)
        Mockito.verify(validNumber)?.checkNumber(4)
        Mockito.verify(validNumber)?.checkNumber(5)
        Mockito.verify(validNumber, Mockito.never())?.checkNumber(9)
        Mockito.verify(validNumber, Mockito.atLeast(1))?.checkNumber(4)
        Mockito.verify(validNumber, Mockito.atMost(3))?.checkNumber(4)

        Mockito.verify(print)?.showMessage(9)
        Mockito.verify(print, Mockito.never())?.showError()

    }

    @Test
    fun addPrintErrorTest() {
        //Given
        given(validNumber?.checkNumber(4)).willReturn(true)
        given(validNumber?.checkNumber("5")).willReturn(false)
        //When
        add!!.addPrint(4, "5")
        //Then
        Mockito.verify(validNumber)?.checkNumber(4)
        Mockito.verify(validNumber)?.checkNumber("5")

        Mockito.verify(print, Mockito.atLeast(1))?.showError()
    }

    @Test
    fun captorTest() {
        //Given
        given(validNumber?.checkNumber(4)).willReturn(true)
        given(validNumber?.checkNumber(5)).willReturn(true)
        //When
        add!!.addPrint(4, 5)
        //Then
        verify(print)?.showMessage(captor!!.capture())
        assertEquals(9, captor!!.value)
    }

    @Spy
    var spyList: MutableList<String> = ArrayList()

    @Mock
    var mockList: MutableList<String> = ArrayList()

    @Test
    fun spyTest() {
        with(spyList) {
            add("1")
            add("2")
        }

        verify(spyList).add("1")
        verify(spyList).add("2")

        assertEquals(2, spyList.size)
    }

    @Test
    fun mockTest() {
        with(mockList) {
            add("1")
            add("2")
        }

        verify(mockList).add("1")
        verify(mockList).add("2")

        given(mockList.size).willReturn(2)

        assertEquals(2, mockList.size)
    }
}