package generic

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