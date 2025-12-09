package `class`

/**
 * record class
 * - equals, hashCode, toString 자동 생성
 */
data class PersonDto(
    val name: String,
    val age: Int
) {
    override fun equals(other: Any?) =
        other is PersonDto && other.name == this.name
}


/**
 * record class 특별 메서드
 */
fun main() {
    val personData1 = PersonDto("강훈", 28)
    val personData2 = PersonDto("강훈", 28)

    //1. 두 인스턴스의 필드가 같다면, hashcode가 같다.
    println("${personData1 == personData2}")
    println("${personData1.hashCode() == personData2.hashCode()}")

    //2. copy() -> 두 객체는 동일
    val personData1Copy = personData1.copy()
    println("${personData1 == personData1Copy}")
    println("${personData1.hashCode() == personData1Copy.hashCode()}")

    //3. component() -> 속성 접근
    println("name = ${personData1.component1()}")
    println("age = ${personData1.component2()}")
}