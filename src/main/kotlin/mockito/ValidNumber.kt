package mockito

import java.lang.ArithmeticException

class ValidNumber {

    fun checkNumber(o: Any): Boolean {
        return if (o is Int) {
            o in 0..9
        } else {
            false
        }
    }

    fun checkZero(o: Any): Boolean {
        return if (o is Int) {
            if (o == 0) {
                throw ArithmeticException("Can't accept zero")
            } else
                true
        } else
            false
    }

    fun doubleToInt(o: Any): Int {
        return if (o is Double) {
            o.toInt()
        } else
            0
    }

}