package ifwhen

/**
 * if when
 * - 코틀린에서 if문과 when 은 표현식으로 간주된다.
 * -> 이 말이 무엇이냐, 바로 값으로 표현할 수 있는 식이라는 것이다.
 * -> 때문에 코틀린에서는 3항 연산자가 삭제되었다.
 */
fun main() {
    getPassOrFail(50)

}

/**
 * if
 */
fun getPassOrFail(score: Int): String {
    return if (score > 50) "P" else "F"
}

/**
 * when
 */
fun getGradeWithSwitch(score: Int): String {
    return when (score) {
        in 90 .. 100 -> "A"
        in 80 .. 90 -> "B"
        in 70 .. 80 -> "C"
        else -> "D"
    }
}

/**
 * when식은 enum 클래스의 모든 타입을 체크하도록 강제한다
 */
fun getGradeEnumWithSwitch(score: Int): Grade {
    return when (score) {
        in 90 .. 100 -> Grade.A
        in 80 .. 90 -> Grade.B
        in 70 .. 80 -> Grade.C
        else -> Grade.D
    }
}

/**
 * when식은 타입 체크도 가능하다
 */
fun case(any: Any) {
    when (any) {
        1 -> println("하나")
        "안녕" -> println("인사")
        is Long -> println("Long")
        !is String -> println("not string")
        else -> println("그 외")
    }
}


enum class Grade() {
    A,
    B,
    C,
    D
}