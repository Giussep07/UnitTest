package mockito

class Add(var validNumber: ValidNumber) {

    fun add(a: Any, b: Any): Int {
        return if (validNumber.checkNumber(a) && validNumber.checkNumber(b)) {
            a as Int + b as Int
        } else
            0
    }
}