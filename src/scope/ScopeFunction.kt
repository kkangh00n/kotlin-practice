package scope

import Person

/**
 * scope function
 */
fun main() {

    /**
     * let -> 범위를 제한하거나 null 검사할 때
     * - 마지막 식을 리턴
     * - 가주어 it 참조
     */
    fun printNonNull(str: String?) {
        val let = str?.let {
            print("let 구문 거침")
            it
        }

        println(let)
    }

    val str: String? = null
    printNonNull(str)


    /**
     * run
     * - 마지막 식을 리턴
     * - this 참조
     */
    fun printLength(ns: String?) {
        val run = ns?.run {
            println("\t비었나? => " + this.isEmpty())
            println("\t길이 => $length")
            length
        }

        println(run)
    }
    printLength(str)

    /**
     * apply
     * - 자기 자신을 리턴
     * - this 참조
     */
    val person: Person? = Person()
    // 인스턴스 생성 후, 프로퍼티 재정의
    val personApply: Person? = person?.apply {
        this.name = "Jake"
        age = 30
    }
    println(personApply?.name)
    println(personApply?.age)

    /**
     * also
     * - 자기 자신을 리턴
     * - 가주어 it 참조
     */
    val person2 = person?.also {
        it.name = "Jake2"
    }
    println(person2?.name)

    /**
     * with - 객체 접근에 유용
     * - 객체 프로퍼티에 접근할 때, 객체 명 생략 가능
     * - nullable 타입 불가
     */
    val person3 = Person("김강훈", 3000)
    with(person3) {
        println("${name}:$age")
    }


}