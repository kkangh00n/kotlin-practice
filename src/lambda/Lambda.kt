package lambda

/**
 * 코틀린 함수 -> 1급 시민 취급
 * - 변수에 담을 수 있음
 * - 함수의 인자로 전달할 수 있음
 * - 함수의 반환 값으로 전달할 수 있음
 *
 * 고차 함수란
 * - 다른 함수를 파라미터로 받거나, 함수를 반환하는 함수
 * - '함수를 값처럼 다룰 수 있다'는 개념 전제
 */
fun main() {

    val fruits = listOf(
        Fruit("바나나", 3000),
        Fruit("사과", 1000)
    )

    /**
     * 기존 함수
     */
    fun isApple(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    /**
     * 고차 함수
     */
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    /**
     * 람다
     */
    //    val isAppleLambda: (Fruit) -> Boolean = { fruit: Fruit -> fruit.name == "사과" }
    val isAppleLambda:  (Fruit) -> Boolean = { it.name=="사과" }

    //함수 파라미터 전달
    filterFruit(fruits, isApple)
    filterFruit(fruits, isAppleLambda)

    /**
     * Closure
     * - Java -> 람다 외부 변수 사용 불가
     * - Kotlin -> 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획하여 그 정보를 갖고 있다
     * 이러한 데이터 구조를 Closure라고 한다.
     */
    val targetFruitName = "사과"
    filterFruit(fruits, { fruit: Fruit -> fruit.name == targetFruitName })
}

/**
 * typealias
 * - 함수 타입에 대한 별칭 설정
 */
typealias FruitFilter = (Fruit) -> Boolean

private fun filterFruit(
    fruits: List<Fruit>,
    /**
     * 함수 파라미터
     */
//    filter: (Fruit) -> Boolean
    filter: FruitFilter
) {

    val results = mutableListOf<Fruit>()

    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
}



class Fruit(
    val name: String,
    val price: Int
)