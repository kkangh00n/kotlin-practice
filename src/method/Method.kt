package method

/**
 * 메서드
 */
fun main() {

    println(max(1,2))

    val name = "홍길동"
    repeat(name)
    repeat(name, 2, false)
    repeat(name, useNewLine = false)
    repeat(useNewLine = false, str = "너")

    val args = arrayOf("A", "B", "C")
    printAll(*args)
}

/**
 * 메서드가 하나의 표현식일 경우
 */
fun max(a: Int, b: Int) = if (a > b) { a } else { b }

/**
 * 메서드 오버로딩
 * - default parameter(파라미터 기본값)으로 해결
 */
fun repeat(
    str: String,
    num: Int = 3,
    useNewLine: Boolean = true
) {

    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        }
        else {
            println(str)
        }
    }
}

/**
 * 가변인자 파라미터
 */
fun printAll(vararg str: String) {
    for (s in str) {
        println(s)
    }
}