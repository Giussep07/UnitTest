package junit5

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import java.lang.ArithmeticException
import java.time.Duration

internal class CalculatorTest {


    companion object {
        private var calculator: Calculator? = null

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            calculator = Calculator()
            println("beforeAll")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            calculator = null
            println("afterAll")
        }
    }

    @BeforeEach
    fun setUp() {
        //calculator = Calculator()
    }

    @AfterEach
    fun tearDown() {
        //calculator = null
    }

    @Test
    fun calculatorNotNullTest() {
        assertNotNull(calculator, "calculator must be not null")
    }

    @Test
    fun addAssertTest() {
        // 1 -> SetUp
        val calculatorAssert = Calculator()
        val expectedResult = 30
        val actualResult: Int
        // 2 -> Action
        actualResult = calculatorAssert.add(10, 20)
        // 3 -> Assert
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun addTest() {
        assertEquals(30, calculator?.add(10, 20))
    }

    @Test
    fun divideValidInputValidExpectedTest() {
        assertEquals(2, calculator?.divide(4, 2))
    }

    @Disabled
    @Test
    fun divideNotValidInputTest() {
        fail<Int>("Fail detected manually, can't divide by zero")
        assertEquals(2, calculator?.divide(5, 0))
    }

    @Test
    fun divideByZeroInvalidInputExpectedExceptionTest() {
        assertThrows(
            ArithmeticException::class.java,
            {
                calculator?.divideByZero(45, 0)
            },
            "Can't divide by zero"
        )
    }

    @Disabled("Disabled until bug 23 be resolved")
    @Test
    fun divideByZeroTest() {
        assertEquals(2, calculator?.divide(5, 0))
    }

    @Test
    fun addAssertAllTest() {
        assertAll(
            { assertEquals(30, calculator?.add(10, 20)) },
            { assertEquals(20, calculator?.add(10, 10)) },
            { assertEquals(2, calculator?.add(1, 1)) }
        )
    }

    @Nested
    inner class AddCreateMock1Test {

        @Test
        fun addPositiveNumberTest() {
            assertEquals(30, calculator?.add(15, 15))
        }

        @Test
        fun addNegativeNumberTest() {
            assertEquals(-30, calculator?.add(-15, -15))
        }

        @Test
        fun addZeroTest() {
            assertEquals(0, calculator?.add(0, 0))
        }
    }

    @Test
    fun timeOutTest() {
        assertTimeout(Duration.ofMillis(1100)) {
            calculator?.longTaskOperation()
        }
    }
}