package generic

import generic.Fish.*

/**
 * 제네릭
 */
fun main() {
    //제네릭 함수
    fun <E> mutableStackOf(vararg elements: E): MutableStack<E> {
        return MutableStack(*elements)
    }

    val mutableStack = mutableStackOf("test")
    println(mutableStack.pop())


    /**
     * 공변 & 반공변
     */
    val goldFishCage = Cage<GoldFish>()
    val goldFish = GoldFish("금붕어")

    goldFishCage.put(goldFish)

    /**
     * 불공변
     * Fish는 GoldFish와 상속 관계이다.
     * Cage<Fish>와 Cage<GoldFish>는 상속 관계가 아니다. (불공변 하다)
     * 때문에 Cage<Fish> 타입에 Cage<GoldFish>를 대입할 수 없다.
     */
    val fishCage = Cage<Fish>()
    //불공변
//    fishCage.moveFromInVariant(goldFishCage)

    /**
     * 공변
     * - out 키워드를 통해 Cage를 공변하게 한다.
     * - out 키워드가 붙은 타입은 데이터를 내보낼 수만 있다.
     */
    //공변
    fishCage.moveFromCoVariant(goldFishCage)

    /**
     * 반공변
     * - in 키워드를 통해 Cage를 반공변하게 한다.
     * - in 키워드가 붙은 타입은 데이터를 입력할 수만 있다.
     */
    //반공변
    goldFishCage.moveFromContraVariant(fishCage)
}

//제네릭 클래스
class MutableStack<E>(vararg items: E) {

    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

//공변 반공변
class Cage<T> {
    private val animals: MutableList<T> = mutableListOf()
    fun getFirst(): T {
        return animals.first()
    }
    fun put(animal: T) {
        this.animals.add(animal)
    }
    fun moveFromInVariant(cage: Cage<T>) {
        this.animals.addAll(cage.animals)
    }

    /**
     * out 변성 어노테이션을 통하여 Cage를 공변하게 한다.
     * out 키워드가 붙은 타입은 데이터를 출력할 수만 있다.
     * -> 매개변수로 들어오는 T 타입은 하위 타입이다.
     * -> <? extends T> 와 동일
     */
    fun moveFromCoVariant(cage: Cage<out T>) {
        this.animals.addAll(cage.animals)
    }

    /**
     * in 변성 어노테이션을 통해 Cage를 반공변하게 한다.
     * in 키워드가 붙은 타입은 데이터를 입력할 수만 있다.
     * -> 매개변수로 들어오는 T 타입은 상위 타입이다.
     * -> <? super T> 와 동일
     */
    fun moveFromContraVariant(cage: Cage<in T>) {
        cage.animals.addAll(this.animals)
    }
}




abstract class Animal(
    val name: String,
)

sealed class Fish(name: String) : Animal(name) {

    // 금붕어
    class GoldFish(name: String) : Fish(name)

    // 잉어
    class Carp(name: String) : Fish(name)
}