package coroutines.strctuedconcurrency

import coroutines.printWithThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Structued Concurrency - 구조화 된 동시성
 * - 코루틴은 내부적으로 트리 구조(부모-자식)의 형태로 관리
 * - 수 많은 코루틴이 유실되거나 누수되지 않도록 보장
 * - 코드 내의 에러가 유실되지 않고 적절히 보고될 수 있도록 보장
 *      - 단, CancellationException는 정상적인 취소로 간주하여, 부모 코루틴에게 전파되지 않음
 */
fun main() {

    /**
     * 구조화 된 동시성
     */
    structuedConcurrency()

    /**
     * 비구조화 된 동시성
     */
    unstructuedConcurrency()
}

/**
 * 구조화 된 동시성
 *
 * 두 코루틴 A,B는 독립적이지 않고, 부모와 연결되어 있다.
 *
 * 동작 과정
 * 1. B에서 예외가 발생
 * 2. 예외가 부모 코루틴에게 전파
 * 3. 부모 코루틴은 자식 코루틴 A에게 예외 전파
 * 4. 부모 코루틴과 하위 코루틴 모두 작업 실패
 */
//부모 코루틴
fun structuedConcurrency() = runBlocking {
    //자식 코루틴 A
    val A = launch {
        delay(600L)
        printWithThread("A")
    }

    //자식 코루틴 B
    val B = launch {
        delay(500L)
        throw IllegalArgumentException("코루틴 실패!")
    }
    B
}

/**
 * 비구조화 된 동시성
 *
 * 각 코루틴이 별도의 root 코루틴으로 구성
 *
 * 동작 과정
 * 1. B에서 예외가 발생
 * 2. 예외는 전파되지 않는다.
 * 3. 자식 코루틴 B만 작업 실패
 */
//부모 코루틴
fun unstructuedConcurrency() = runBlocking {
    //별도의 root 코루틴
    val A = CoroutineScope(Dispatchers.Default).launch {
        delay(600L)
        printWithThread("A")
    }

    //별도의 root 코루틴
    val B = CoroutineScope(Dispatchers.Default).launch {
        delay(500L)
        throw IllegalArgumentException("코루틴 실패!")
    }
}