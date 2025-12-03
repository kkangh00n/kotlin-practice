package range

/**
 * 범위 표현식
 */
fun main() {

    /**
     * 1 <= num <= 5
     */
    val range1 = 1..5
    for (num in range1) {
        println(num)
    }

    /**
     * 1 <= num < 5
     */
    val range2 = 1 until 5
    for (num in range2) {
        println(num)
    }

    /**
     * 5 >= num >= 1
     */
    val range3 = 5 downTo 1
    for (num in range3) {
        println(num)
    }
}