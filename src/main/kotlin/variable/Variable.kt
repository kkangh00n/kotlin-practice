package variable

import Person

fun main() {
    /**
     * 1. var & val
     */
    // var - 변경 가능
    var number1 = 5L
    number1 = 6L
    println(number1)

    // val - final
    val number2 = 7L
//    val number2 = 8L
    println(number2)

    /**
     * 2. primitive type
     *
     * - 코틀린에서는 boxing/unboxing을 고려하지 않도록 알아서 처리
     */

    var number3: Long = 10L

    /**
     * 3. nullable type
     * - nullable 한 type은 뒤에 '?' 붙임
     */
    var number4: Long? = null
    println(number4)

    /**
     * 4. 객체 인스턴스화
     * - new 키워드 X
     */
    val person = Person("김강훈", 28)
    println(person.name)

}