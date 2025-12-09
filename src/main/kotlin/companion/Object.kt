package companion

import lec12.Moveable

/**
 * object 키워드 쓰임새
 * 1. 싱글톤 객체
 * 2. 익명 클래스
 */
fun main() {

    println(Singleton.a)

    /**
     * 익명 클래스
     */
    moveSomething(object: Moveable {
        override fun move() {
            println("움직인당")
        }

        override fun fly() {
            println("난당")
        }
    })
}

/**
 * 싱글톤 객체
 * 자체로 고유한 객체
 */
object Singleton {
    var a: Int = 151
}

private fun moveSomething(moveable: Moveable) {
    moveable.move()
    moveable.fly()
}