package mockito

class ValidNumber {

    fun checkNumber(o: Any): Boolean {
        return if (o is Int) {
            o in 0..9
        } else {
            false
        }
    }

}