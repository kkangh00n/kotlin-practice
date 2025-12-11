package coroutines.coroutinebuilder

import coroutines.printWithThread
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * async
 * - 반환 값이 존재하는 코루틴을 실행할 때 사용
 * - Deferred 객체를 통해 결과 반환
 * - Deferred 객체를 통해 코루틴 제어 가능
 */
fun main(): Unit = runBlocking {

    val deferred = async { apiCall1() }
    val deferred2 = async { apiCall2() }

    /**
     * 코루틴 반환 값 조회
     */
    printWithThread(deferred.await() + deferred2.await())

}

suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_000L)
    return 2
}