package operator

/**
 * 연산자 함수
 * 연산자 함수는 예약되어 있는 함수 이름을 사용해서 오버로딩이 가능
 *
 * + -> plus
 * - -> minus
 * * -> times
 * / -> div
 * % -> rem
 * += -> plusAssign
 * -= -> minusAssign
 * *= -> timesAssign
 * /= -> divAssign
 * %= -> remAssign
 */
operator fun Int.times(str: String) = str.repeat(this)

fun main() {
    println(3 * "3번반복 ")

}