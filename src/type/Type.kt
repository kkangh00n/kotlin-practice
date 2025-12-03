package type

import Person
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

fun main() {

    /**
     * 1. 타입 변환
     */
    val number1: Int = 3
    val number1ToLong = number1.toLong()
    println(number1ToLong)

    val number1ToDouble = number1.toDouble()
    println(number1ToDouble)

    /**
     * 2. 특별한 타입 3가지
     *
     * - Any: Java의 Object
     * - Unit: void
     * - Nothing: 무조건 예외를 반환하거나, 무한 루프 함수
     */


    /**
     * 4. 스마트 캐스트
     * 타입을 확인하게 되면 해당 블록 내에서 타입 변환
     */
    val date: ChronoLocalDate? = LocalDate.now()
    if (date is LocalDate) {
        val month = date.monthValue //-> LocalDate 타입
        println(month)
    }
}

/**
 * 3. 타입 캐스팅
 */
fun printAgeIfPerson(obj: Any) {

    /**
     * is -> instanceOf
     */
    if (
        obj is Person
        //obj !is Person
    ) {
        /**
         * as -> 타입 변환
         */
        val person = obj as Person
        println(person.age)
    }
}