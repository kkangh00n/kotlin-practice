package `class`

/**
 * 상속
 */
fun main() {
    val base = Base()

    //1. Base 초기화
    //2. Base에서 Derived.number 접근
    //3. Derived.number 초기화 전
    //4. 때문에 상위 클래스의 init 블록에서 프로퍼티 접근 시 0 출력
    val derived = Derived()

    println(base.number)
    println(derived.number)
}

/**
 * 일반 클래스 상속
 * - open 키워드를 통해서 타 클래스의 상속을 허용
 * - 그렇지 않으면 상속 불가
 */
open class Base(
    open val number: Int = 100
) {
    init {
        println("Base class")
        println(number)
    }
}

class Derived(
    override val number: Int = 300
) : Base(number = number) {
    init {
        println("Derived class")
    }
}


/**
 * 추상 클래스
 * - open 키워드를 통해서 타 클래스의 상속을 허용
 * - 그렇지 않으면 상속 불가
 */
abstract class Animal(
    protected val species: String,
    protected open val legCount: Int
) {

    abstract fun move()

}

class Cat(
    species: String
) : Animal(species, 4) {

    override fun move() {
        println("사뿐사뿐")
    }

}