package inline

import lambda.Fruit

/**
 * 인라인 함수
 * - 함수 호출 위치에 본문을 복사
 * - 인라인 함수를 사용하여 람다를 사용함에 따라 발생할 수 있는 성능상 부가 비용을 없앨 수 있음
 *
 * 어떤 부가 비용?
 * - 람다 호출 시, 매번 무명 함수 객체로 변환하게 되어 메모리 낭비
 *
 * tools -> kotlin -> show kotlin bytecode
 */
inline fun filterFruit(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean
) {

    val results = mutableListOf<Fruit>()

    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
}

fun main() {
    val fruits = listOf(
        Fruit("바나나", 3000),
        Fruit("사과", 1000)
    )

    /**
     * 인라인 함수일 경우 -> 함수의 본문이 이곳에 복사
     * 인라인 함수가 아닐 경우 -> 람다 함수를 무명 객체로 생성하여 함수의 파라미터로 넘김
     */
    filterFruit(fruits, { fruit: Fruit -> fruit.name == "사과" })
}