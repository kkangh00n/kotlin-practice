package array

/**
 * 배열
 */
fun main() {
    /**
     * 배열 생성
     */
    var array = arrayOf(100, 200)

    /**
     * 배열 값 조회
     */
    println(array[0])

    /**
     * indices -> 인덱스 번호
     */
    for (i in array.indices) {
        println("${i}, ${array[i]}")
    }

    /**
     * 배열의 값 추가 후, 새로운 배열 반환
     */
    println("배열의 값 추가 후, 새로운 배열 반환")
    array = array.plus(300)

    /**
     * withIndex -> 인덱스 및 값 함께
     */
    for ((idx, value) in array.withIndex()) {
        println("${idx}, ${value}")
    }
}