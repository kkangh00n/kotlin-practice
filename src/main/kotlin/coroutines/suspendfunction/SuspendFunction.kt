package coroutines.suspendfunction

import coroutines.printWithThread
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.CompletableFuture

/**
 * suspend function - 중단 함수
 * - 다른 suspend function을 호출할 수 있는 능력이 생긴다.
 * - 코루틴이 중지되었다가 재개될 수 있는 지점인 suspension point 이다.
 *      -> 무조건 중지되는 것이 아닌, 중지가 될 수도 있고 되지 않을 수도 있다는 의미
 */

fun main() {
    /**
     * suspend function을 통해 다른 비동기 라이브러리 구현체를 사용할 수 있다.
     */
//    runBlocking {
//        val result1 = coroutineFunc()
//        val result2 = completableFunc(result1)
//        printWithThread(result2)
//    }

    /**
     * 코루틴 라이브러리에서 제공하는 suspend function
     *
     * 1. coroutineScope
     * 2. withContext
     * 3. withTimeout
     * 4. withTimeoutOrNull
     */
    runBlocking {
        printWithThread("START")
//        printWithThread(coroutineScope())
//        printWithThread(withContext())
//        printWithThread(withTimeout())
//        printWithThread(withTimeoutOrNull())
        printWithThread("END")
    }

}

//코루틴이 중단될 수도 있는 지점
// Coroutine 사용
suspend fun coroutineFunc(): Int {
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(
            1_000L
        )
        100
    }.await()
}

//코루틴이 중단될 수도 있는 지점
// CompletableFuture 사용
suspend fun completableFunc(num: Int): Int {
    return CompletableFuture.supplyAsync {
        Thread.sleep(
            1_000L
        )
        100
    }.await()
}


/**
 * 1. coroutineScope
 * - 여러 동시 작업을 그룹화하고 모두 완료될 때까지 기다리는 스코프 함수
 * - 진입 시, 바로 실행
 * - 내부의 모든 자식 코루틴이 완료될 때까지 자동으로 대기
 */
suspend fun coroutineScope(): Int = coroutineScope {
    println("coroutineName : ${coroutineContext[CoroutineName.Key]}")

    val num1 = async {
        delay(1_000L)
        10
    }
    val num2 = async {
        delay(1_000L)
        20
    }
    num1.await() + num2.await()
}

/**
 * 2. withContext
 * - CoroutineContext에 변화를 줄 수 있음.
 * - 그 이외는 coroutineScope와 동일
 */
suspend fun withContext(): Int = withContext(CoroutineName("코루틴 이름")) {
    println("coroutineName : ${coroutineContext[CoroutineName.Key]}")

    val num1 = async {
        delay(1_000L)
        10
    }
    val num2 = async {
        delay(1_000L)
        20
    }
    num1.await() + num2.await()
}

/**
 * 3. withTimeout
 * - 주어진 시간 내에 완료되지 않으면, Exception 던짐
 * - 그 이외는 coroutineScope와 동일
 */
suspend fun withTimeout(): Int = withTimeout(1_000L) {

    val num1 = async {
        delay(1_000L)
        10
    }
    val num2 = async {
        delay(1_000L)
        20
    }
    num1.await() + num2.await()
}

/**
 * 3. withTimeoutOrNull
 * - 주어진 시간 내에 완료되지 않으면, Exception 던짐
 * - 그 이외는 coroutineScope와 동일
 */
suspend fun withTimeoutOrNull(): Int? = withTimeoutOrNull(1_000L) {

    val num1 = async {
        delay(1_000L)
        10
    }
    val num2 = async {
        delay(1_000L)
        20
    }
    num1.await() + num2.await()
}