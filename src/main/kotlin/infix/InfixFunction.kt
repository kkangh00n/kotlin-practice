package infix

fun main() {

    //중위 함수 호출
    val infixResult = 1 plus 2
    println(infixResult)

}

/**
 * 중위 함수
 * - 함수 부르는 방법을 다르게 할 수 있음.
 */
infix fun Int.plus(num: Int): Int {
    return this+num
}