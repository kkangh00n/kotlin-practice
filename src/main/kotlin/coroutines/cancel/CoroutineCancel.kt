package coroutines.cancel

import coroutines.printWithThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

/**
 * 코루틴 취소
 * - 컴퓨팅 자원을 적절하게 관리하기 위해서 코루틴을 취소할 수 있어야 한다.
 * - Job 객체의 cancel() 함수를 통해 취소 가능
 *
 * - 하지만 취소 대상 코루틴도 협조를 해주어야 한다!
 * - 취소 협조 정루
 * 1. 코루틴 내부에서 suspend 함수 사용 - delay(), yield() 등
 * 2. throwCancellationException
 *
 */
fun main() {

    /**
     * 0. 취소 협조 X
     */
    noCancel()

    /**
     * 1. suspend 함수를 통한 취소 협조
     */
    useSuspendFunction()

    /**
     * 2. CancellationException
     */
    throwCancellationException()

}

fun noCancel() = runBlocking {
    /**
     * 취소 협조 X
     */
    val job = launch {
        printWithThread("Job 실행")
    }

    delay(500L)

    // job 취소 요청
    job.cancel()
}


fun useSuspendFunction() = runBlocking {
    val job1 = launch {
        /**
         * delay() -> suspend 함수
         */
        delay(1_000L)
        printWithThread("Job 실행")
    }

    delay(500L)

    // job 취소 요청
    job1.cancel()
}


fun throwCancellationException() = runBlocking {

    //Dispatchers.Default -> 별도 스레드 배정
    val job = launch(Dispatchers.Default) {
        var i = 1
        while (i <= 5) {
            /**
             * throw CancellationException()
             */
            if (i == 3) {
                throw CancellationException()
            }
            printWithThread("${i++} 번째 출력!")
            delay(500L)
        }
    }

    //코루틴 내부에서 CancellationException 발생 대기
    delay(2_000L)

    // job 취소 요청
    printWithThread("취소 시작")
    job.cancel()
}
