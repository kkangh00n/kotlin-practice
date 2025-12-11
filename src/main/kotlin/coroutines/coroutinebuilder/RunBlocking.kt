package coroutines.coroutinebuilder

import coroutines.printWithThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * runBlocking
 * - 새로운 코루틴을 만든다.
 * - 내부의 코루틴과 로직이 모두 완료될 때까지 스레드를 Block
 * - 그렇기에 프로그램에 진입하는 최초의 메인 함수나 테스트 코드를 시작할 때만 사용하는 것이 좋음
 */
fun main() {

    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L)
            printWithThread("LAUNCH END")
        }
    }

    //runBlocking이 종료 후 접근
    printWithThread("END")
}