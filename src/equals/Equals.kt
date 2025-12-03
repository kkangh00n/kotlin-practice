package equals

import java.util.*

/**
 * 동일성, 동등성
 */
fun main() {
    val money1 = Money(1000L)
    val money2 = Money(1000L)

    /**
     * 동일성 (==) -> equals
     */
    println(money1 == money2)

    /**
     * 동등성 (===) -> is
     */
    println(money1 === money2)

}

class Money(
    val value: Long
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val javaMoney: Money = o as Money
        return value == javaMoney.value
    }

    override fun hashCode(): Int {
        return Objects.hash(value)
    }
}