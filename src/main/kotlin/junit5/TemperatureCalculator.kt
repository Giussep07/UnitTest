package junit5

class TemperatureCalculator {

    fun toFahrenheit(degree: Float): Float {
        return degree * 9 / 5 + 32
    }
}