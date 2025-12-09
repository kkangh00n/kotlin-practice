package collection

/**
 * collection
 */
fun main() {
    /**
     * list
     */
    //불변 list 선언
    val numbers = listOf(100, 300)
    val numbers2 = listOf("a", "b", "C")
    val emptyList = emptyList<Int>()

    //가변 list 선언
    val otherNumbers = mutableListOf<Int>()

    //요소 추가
    otherNumbers.add(100)

    //요소 접근
    println(numbers[0])

    for (number in numbers) {
        println(number)
    }

    /**
     * map
     */
    //불변 map 선언
    val immutableMap = mapOf(1 to "MonDay")

    //가변 map 선언
    val map = mutableMapOf<Int, String>()

    //요소 추가
    map[1] = "monday"
    map[2] = "tuesday"

    //요소 접근
    println(map[1])
    println(map.get(1))

    //요소 제거
    map.remove(2)
    println(map[2])

    //getOrElse(), getOrDefault()
    map.getOrElse(3, { "thursday" })
    map.getOrDefault(3, "thursday")

    //withDefault
    // -> 존재하지 않는 키가 들어올 때, default 값을 설정한 map 선언
    val anotherMap: Map<Int, String> = map.withDefault { key -> "기본값" }
    println("get: ${anotherMap.get(5)}")
    println("withDefault: ${anotherMap.getValue(5)}")

    //key 목록
    val keys = map.keys

    //key value 접근
    for ((key, value) in map.entries) {
        println("${key} - ${value}")
    }

    /**
     * set
     */
    //불변 set 선언
    val sets = setOf(100, 100)

    //가변 set 선언
    val mutableSet = mutableSetOf(100, 200)

    //요소 추가
    mutableSet.add(300)

    //요소 삭제
    mutableSet.remove(300)

    //withIndex()
    for ((idx, num) in mutableSet.withIndex()) {
        println("${idx}  ${num}")
    }

    /**
     * zip()
     * 두 컬렉션을 합쳐서 pair 형태로 변환
     * 있는 만큼만 pair 쌍 이룸
     */
    val zip = numbers.zip(numbers2)
    val zip2 = numbers2 zip numbers
    println("zip: ${zip}")
    println("zip2: ${zip2}")

}