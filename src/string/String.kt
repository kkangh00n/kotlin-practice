package string

import Person

/**
 * 문자열 표현
 */
fun main() {
    val person = Person("김강훈", 19)

    /**
     * 1. 변수 표현
     */
    println("이름: ${person.name} 나이: ${person.age}")

    /**
     * 2. indent 문자열 표
     */
    val str = """
        ABC
        EFG
        ${person.name}
    """.trimIndent()
    println(str)
    println(str[0])
    println(str[1])
}