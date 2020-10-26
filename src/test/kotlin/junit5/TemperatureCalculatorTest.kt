package junit5

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TemperatureCalculatorTest {

    private var temperatureCalculator: TemperatureCalculator? = null

    @BeforeEach
    fun setUp() {
        temperatureCalculator = TemperatureCalculator()
        println("@BeforeEach -> setUp()")
    }

    @AfterEach
    fun tearDown() {
        temperatureCalculator = null
        println("@AfterEach -> tearDown()")
    }

    @Test
    fun toFahrenheitTest() {
        assertEquals(-9.4f, temperatureCalculator?.toFahrenheit(-23f)!!, 0.01f)
    }

}