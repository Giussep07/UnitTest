package junit5

import java.lang.ArithmeticException

class Calculator {

    fun add(number1: Int, number2: Int): Int {
        return number1 + number2
    }

    fun subtract(number1: Int, number2: Int): Int {
        return number1 - number2
    }

    fun divide(number1: Int, number2: Int): Int {
        return number1 / number2
    }

    fun divideByZero(number1: Int, number2: Int): Int {
        if (number2 == 0) {
            throw  ArithmeticException("can't divide by zero")
        }
        return number1 / number2
    }

    fun longTaskOperation() {
        Thread.sleep(1000)
    }
}