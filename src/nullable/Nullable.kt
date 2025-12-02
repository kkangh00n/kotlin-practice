package nullable

fun main() {

    /**
     * 1. safe call
     * - null이 아닌 경우에만 호출
     */
    var str: String? = "ABC"
    println(str?.length)

    str = null
    println(str?.length)

    /**
     * 2. Elvis 연산자 (?:)
     * - null일 경우에만 호출
     */
    var str2: String? = null
    println(str2?.length ?: 0)

    /**
     * 3. !!
     * - nullable type 이지만 무조건 값이 있는 경우
     */
    var str3: String? = "ABCD"
    println(str3!!.length)

}