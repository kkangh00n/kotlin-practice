package coroutines.coroutinescope

import coroutines.printWithThread
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * CoroutineScope, CoroutineContext
 */
fun main() {

    /**
     * CoroutineScope
     */
    makeCoroutineScope()

    /**
     * CoroutineScope의 영역 자체를 cancel 시켜서, 모든 코루틴을 종료할 수도 있다.
     */
    val asyncLogic = AsyncLogic()
    asyncLogic.play()

    /**
     * CoroutineContext
     */
    coroutineContext()

    /**
     * Dispatcher를 통해 스레드 풀 설정하는 법
     */
    dispatcher()

}

/**
 * CoroutineScope - 코루틴이 탄생할 수 있는 영역
 * - launch, async와 같은 코루틴 빌더는 CoroutineScope의 확장 함수
 * - CoroutineScope는 다음과 같이 제공받을 수 있다.
 *      1. runBlocking 내에서 제공
 *      2. 직접 CoroutineScope 생성
 * # 사실 직접 CoroutineScope를 생성한다면, runBlocking이 필요하지 않다.
 * - Structured Concurrency는 CoroutineScope를 통해 정해진다.
 *      -> Structured Concurrency -> 같은 CoroutineScope 내에서 관리되는 Coroutine의 관계
 */
fun makeCoroutineScope() = runBlocking {
    //자식 코루틴
    launch {
        printWithThread("자식 코루틴")
    }

    //별도의 root 코루틴
    CoroutineScope(Dispatchers.Default).launch {
        printWithThread("별도의 root 코루틴")
    }
}

/**
 * CoroutineScope의 영역 자체를 cancel 시켜서, 모든 코루틴을 종료할 수도 있다.
 */
class AsyncLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    private fun doSomething() {
        scope.launch {
            delay(1_000L)
            println("작업 완료")
        }
    }

    private fun elseSomething() {
        scope.launch {
            delay(1_000L)
            println("작업 완료")
        }
    }

    private fun destroy() {
        //scope 취소
        println("모든 작업 취소")
        scope.cancel()
    }

    fun play() {
        doSomething()
        elseSomething()
        destroy()
    }
}


/**
 * CoroutineContext - 코루틴과 관련된 데이터
 * - CoroutineScope 주요 역할 -> CoroutineContext를 보관하는 것
 * - CoroutineContext 포함 정보
 *      1. 코루틴 이름
 *      2. CoroutineExceptionHandler
 *      3. Dispatcher
 * - 자식 코루틴이 생성될 경우, 부모 코루틴의 context를 가져와 적절히 덮어써서 새로운 context를 갖게 된다.
 *      -> Structured Concurrency 원리
 */

/**
 * Element - CoroutineContext 구성요소
 * - key-value 형태 (key 별로 고유)
 * - Element끼리 합쳐서 새로운 CoroutineContext를 생성하거나, 기존의 CoroutineContext에 추가할 수 있다.
 */
fun coroutineContext() {
    //1. Element끼리 결합
    val newContext = CoroutineName("나만의 코루틴") + SupervisorJob()

    //2. CoroutineContext에 Element 추가
    val addContext = newContext + Dispatchers.Default

    runBlocking {
        //3. CoroutineScope 생성
        val job =
            CoroutineScope(addContext).launch {
                //4. CoroutineScope 내에서 context 접근
                println("Element 존재 : ${coroutineContext[CoroutineName]}")
            }

        job.join()

        //4. Element 제거
        val minusKeyContext = coroutineContext.minusKey(CoroutineName.Key)
        CoroutineScope(minusKeyContext).launch {
            println("Element 제거 후 : ${coroutineContext[CoroutineName]}")
        }

    }
}


/**
 * Dispatcher
 */
fun dispatcher() {

    /**
     * 1. Default -> 가장 기본적인 디스패처. CPU 자원을 많이 쓸 때 권장
     * 2. IO -> I/O 작업에 최적화된 디스패처
     * 3. Main -> UI 컴포넌트를 조작하기 위한 디스패처. 특정 의존성 필요
     */
    val coroutineScope = CoroutineScope(Dispatchers.Default)


    /**
     * 4. ExecutorService를 디스패처로 전환
     */
    val threadPool = Executors.newSingleThreadExecutor()

    //ExecutorService를 통해 만든 customThreadPool 삽입
    val customCoroutineScope = CoroutineScope(threadPool.asCoroutineDispatcher())
}