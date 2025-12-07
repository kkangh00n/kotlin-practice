package `class`

/**
 * sealed class
 * - 클래스 상속을 특정 범위로 제한
 * - 특정 타입이 가질 수 있는 모든 하위 타입을 컴파일 타임에 알 수 있도록 제한
 */

/**
 * 1. 안전한 when 표현식
 *      -> 모든 타입을 다루면, else 필요 없음
 * 2. enum 클래스 한계 극복
 *      -> 각 하위 타입이 속성을 가질 수 있음
 */
sealed class Mammal(val name: String)
{
    class Cat(val catName: String) : Mammal(catName)
    class Human(val humanName: String, val job: String) : Mammal(humanName)
}

fun main(mammal: Mammal): String {
    when (mammal) {
        is Mammal.Human ->
            return "안녕하세요, ${mammal.name}님. 직업은 ${mammal.job}이군요."
        is Mammal.Cat ->
            return "안녕 ${mammal.name}"
    }
}