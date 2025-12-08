package collection

/**
 * collection method
 */
fun main() {
    val fruits = listOf<Fruit>(
        Fruit("바나나", 3000),
        Fruit("사과", 1000),
        Fruit("사과", 2000)
    )

    /**
     * filter, filterIndexed
     */
    fruits.filter { fruit -> fruit.name == "사과" }
    fruits.filterIndexed { idx, fruit ->
        println(idx)
        fruit.name == "사과"
    }

    /**
     * map, mapIndexed
     */
    fruits.map { fruit -> fruit.price }
    fruits.mapIndexed { idx, fruit ->
            println(idx)
            fruit.price
        }

    /**
     * all, none, any
     */
    //모두 조건을 만족하는지
    fruits.all { fruit -> fruit.name == "사과" }
    //모두 조건을 불만족하는지
    fruits.none { fruit -> fruit.name == "사과" }
    //하나라도 조건을 만족하는지
    fruits.any { fruit -> fruit.name == "사과" }

    /**
     * find
     * - 요소를 찾지 못하면 null
     */
    val find: Fruit? = fruits
        .find { fruit -> fruit.name.startsWith("사") }
    println(find)
    val find2: Fruit? = fruits
        .find { fruit -> fruit.price > 0 }
    println(find2)

    /**
     * count
     */
    val count1 = fruits.count()
    println(count1)
    val count2 = fruits.count({ fruit -> fruit.name == "사과" })
    println(count2)

    /**
     * sortedBy, sortedByDescending
     */
    val sortedBy = fruits.sortedBy { fruit -> fruit.price }
    println(sortedBy)
    val sortedByDescending = fruits.sortedByDescending { fruit -> fruit.price }
    println(sortedByDescending)

    /**
     * distinctBy
     */
    fruits.distinctBy { fruit -> fruit.name }

    /**
     * first, last
     * - find()함수와 다른점
     * -> 리스트의 조건을 만족하는 요소가 없다면 예외 발생!
     */
    fruits.first()
    fruits.first { fruit -> fruit.name.startsWith("사") }
    fruits.last()
    fruits.last { fruit -> fruit.name.startsWith("사") }

    /**
     * firstOrNull, lastOrNull
     */
    fruits.firstOrNull()
    fruits.firstOrNull { fruit -> fruit.name.startsWith("사") }
    fruits.lastOrNull()
    fruits.lastOrNull { fruit -> fruit.name.startsWith("사") }

    /**
     * associateBy
     * - Map 타입 변환
     * - key 하나 당 value 하나
     * - 중복 키 불가
     */
    //(name, Fruit)
    fruits.associateBy { fruit -> fruit.name }
    //(name, price)
    fruits.associateBy(Fruit::name, Fruit::price)

    /**
     * groupBy
     * - Map 타입 변환
     * - key 하나 당 value 여러 개 그룹화
     * - 중복 키 가능
     */
    //(name, List<Fruit>)
    //이름을 기준으로, Fruit 리스트 생성
    fruits.groupBy { fruit -> fruit.name }
    //(name, List<price>)
    //이름을 기준으로, 가격 리스트 생성
    fruits.groupBy({fruit -> fruit.name}, {fruit -> fruit.price})


    /**
     * partition
     * - 특정 기준 조건을 통해 2개의 list로 분할
     */
    val (apples, nonApple) = fruits.partition { fruit -> fruit.name.equals("사과") }
    apples.forEach { apple -> println(apple.name) }
    nonApple.forEach { fruit -> println(fruit.name) }

    /**
     * flatten
     * - List<List<Fruit>> -> List<Fruit> 변환
     */
    listOf(fruits).flatten()

    /**
     * flatMap
     * - List<Map<K, V>> -> List<V> 변환
     */
    val listMap: List<Map<String, Fruit>> = listOf(fruits.associateBy { fruit -> fruit.name })
    val result: List<Fruit> = listMap.flatMap { fruits }
    result.forEach { println(it) }

    /**
     * maxByOrNull, minByOrNull
     */
    val min = fruits.minByOrNull { fruit -> fruit.price }
    val max = fruits.maxByOrNull { fruit -> fruit.price }
    println(min?.price)
    println(max?.price)
}