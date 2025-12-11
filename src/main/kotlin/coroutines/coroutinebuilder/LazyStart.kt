package coroutines.coroutinebuilder

import coroutines.printWithThread
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * CoroutineStart.LAZY
 * - 코루틴 지연 시작
 * - start() 함수를 통해 코루틴 시작 명령
 */
fun main(): Unit = runBlocking {

    /**
     * CoroutineStart.LAZY -> 지연 실행
     */
    val job = launch(start = CoroutineStart.LAZY) {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    repeat(3) {
        println("${3-it}뒤에 시작합니다.")
        delay(1_000L)
    }

    /**
     * 코루틴 실행 시작
     */
    job.start()

}
