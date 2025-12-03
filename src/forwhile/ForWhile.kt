package forwhile

/**
 * for while
 */
fun main() {
    /**
     * 범위 표현식을 통해 반복문
     */
    for (num in 1..4){
        println(num)
    }

    /**
     * 범위 내에서 2칸씩 이동할 경우
     */
    for (num in 1..10 step 2) {
        println(num)
    }

    for (num in listOf(1,2,3,4,5)) {
        println(num)
    }


    var idx = 1
    while (idx < 5) {
        println(idx)
        idx++
    }
}