package lateinitLazy

import kotlin.properties.Delegates.notNull

/**
 * 지연과 위임
 */
fun main() {
    val person: Person = Person()
    person.name = "김강훈"
    println(person.name)

}


class Person{
    /**
     * lateinit
     * - 객체 인스턴스화 시점과 변수 초기화 시점을 분리할 수 있다.
     * - 변수 초기화 전 호출 시, 예외 발생
     * - primitive 타입은 선언 불가능
     */
    lateinit var name: String

    /**
     * notNull() 메서드를 통해 lateinit 가능
     * by 위임을 이용
     */
    var age: Int by notNull()

    /**
     * by lazy
     * - 변수 호출 직전 한 번만 초기화
     */
    val height: Int by lazy {
        Thread.sleep(1000)
        180
    }


    val isKim: Boolean
        get() = this.name.startsWith("김")
    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}