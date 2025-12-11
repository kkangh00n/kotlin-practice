package coroutines.coroutinebuilder

import coroutines.printWithThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch
 * - 반환 값이 없는 코루틴을 실행할 때 사용
 * - Job 객체를 통해 코루틴 제어 가능
 */
fun main(): Unit = runBlocking {

    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    /**
     * 코루틴이 모두 실행될 때까지 대기
     */
//    job.join()

    delay(1_500L)

    /**
     * 코루틴 실행 취소
     */
    job.cancel()
}
