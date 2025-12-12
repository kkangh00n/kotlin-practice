package coroutines.exception

import coroutines.printWithThread
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.coroutines.cancellation.CancellationException

/**
 * 코루틴의 예외 처리
 */

/**
 * 코루틴 Life Cycle
 * New -> Active -> (Completing) -> Completed
 * 예외 발생 -> Cancelling -> Cancelled
 *
 * Completing을 거쳐 가는 이유는?
 * - 코루틴이 자식 코루틴을 갖고 있을 경우, 자식 코루틴들 중 하나에서 예외가 발생하면, 다른 자식 코루틴들에게도 취소 요청을 보내기 위함!!!
 */

/**
 * 취소와 예외
 *
 * 1. CancellationException 예외가 발생한 경우
 * - 취소로 간주하고 부모 코루틴에게 전파되지 않음.
 *
 * 2. 다른 예외가 발생한 경우
 * - 실패로 간주하고 부모 코루틴에게 전파
 * - 부모 코루틴은 다른 자식 코루틴을 취소 요청
 * -> 부모 코루틴은 성공하더라도, 자식 코루틴에게 의해 실패될 수 있기에 Completing 상태 존재!!
 */
fun main() {

    /**
     * 자식 코루틴 예외 시
     * - 예외 발생 즉시 부모 코루틴으로 전파
     * - 형제 코루틴의 남은 작업들은 처리되지 않음!
     * - async, launch 모두 동일하게 동작
     */
//    childThrow()

    /**
     * root 코루틴 예외 시
     * - 예외를 코루틴 별로 관리
     *
     * launch - 예외가 발생하더라도 다른 코루틴 작업은 성공
     * async - await() 함수를 통해 예외 발생 확인, 예외가 발생하더라도 다른 코루틴 작업 진행
     */
//    rootLaunchThrow()
//    rootAsyncThrow()


    /**
     * 자식 코루틴 예외 처리 방법 - SupervisorJob
     * - 형제 코루틴에게 예외를 전파하지 않음.
     */

    /**
     * case 1
     */
//    supervisorJob1()

    /**
     * case 2
     */
//    supervisorJob2()


    /**
     * 코루틴에서 발생한 예외를 핸들링하고 싶다면
     */
    //1. try-catch
//    tryCatch()

    //2. CoroutineExceptionHandler
    //- 부모 코루틴이 있으면 동작하지 않음.
    //- launch에만 적용 가능
//    exceptionHander()


    /**
     * Gracefully shut down
     * - 코루틴 취소 시, finally 절을 통한 자원 해제
     */
//    graceFullyShutDown()
}


/**
 * =======================================================================
 */


fun childThrow() = runBlocking {
    val successJob = async {
        delay(200L)
        printWithThread("다른 작업 성공")
    }

    val exceptionJob = async {
        delay(100L)
        throw IllegalArgumentException()
    }

}

fun rootLaunchThrow() = runBlocking {
    val asyncJob = async {
        delay(200L)
        printWithThread("다른 작업 성공")
    }

    val rootLaunchJob = CoroutineScope(Dispatchers.Default).launch {
        delay(100L)
        throw IllegalArgumentException()
    }
}

fun rootAsyncThrow(): Unit = runBlocking {
    val launchJob = launch {
        delay(200L)
        printWithThread("다른 작업 성공")
    }

    val rootAsyncJob = CoroutineScope(Dispatchers.Default).async {
        delay(100L)
        throw IllegalArgumentException()
    }

    //이 때 예외 발생
    rootAsyncJob.await()

}

fun supervisorJob1() = runBlocking {
    val supervisorJob = SupervisorJob()

    val parent = CoroutineScope(supervisorJob)

    //supervisorJob 적용 - job2에 예외 전달 X
    val job1 = parent.launch {
        delay(50)
        println("Job1 throwing error")
        throw RuntimeException("Job1 throwing error")
    }

    //job2 작업 정상 종료
    val job2 = launch {
        try {
            delay(200)
            println("Job 2 Done")
        } catch (e: CancellationException) {
            println("Job 2 Cancelled")
        }
    }

    job1.join()
    job2.join()
}

fun supervisorJob2() = runBlocking {
    val supervisorJob = SupervisorJob()

    val parent = CoroutineScope(supervisorJob)

    //supervisorJob 적용 - job4에 예외 전달 X
    val job3 = parent.launch {
        //supervisorJob 미적용 - job2에 예외 전달
        val job1 = launch {
            delay(50)
            println("Job1 throwing error")
            throw RuntimeException("Job1 throwing error")
        }

        val job2 = launch {
            try {
                delay(200)
                println("Job 2 Done")
            } catch (e: CancellationException) {
                println("Job 2 Cancelled")
            }
        }
    }
    job3.join()

    //job4 작업 정상 종료
    val job4 = launch {
        try {
            delay(200)
            println("Job 4 Done")
        } catch (e: CancellationException) {
            println("Job 4 Cancelled")
        }
    }
}

fun tryCatch() = runBlocking {
    val job = launch { // async로 변경해도 동일
        try {
            throw IllegalArgumentException()
        } catch (e: IllegalArgumentException) {
            printWithThread("정상 종료")
        }
    }
}

fun exceptionHander() = runBlocking {
    //파라미터 -> 코루틴 구성 요소, 발생한 예외
    val exceptionHandler = CoroutineExceptionHandler { context, ex ->
        printWithThread("예외 = ${ex}")
    }

    //CoroutineExceptionHandler 전달
    // - 부모 코루틴이 존재할 경우, 동작하지 않음.
    val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        throw IllegalArgumentException("exception message")
    }

    delay(1_000L)
}

fun graceFullyShutDown() = runBlocking {
    val file = File("test.txt")
    file.writeText("hello world")
    val reader = BufferedReader(FileReader(file))

    val job = launch {
        try {
            repeat(1000) {
                val line = reader.readLine()
                if (line == null) return@repeat
                print(line)
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("clean up")
                reader.close()
                println("clean up end")
            }
        }
    }

    delay(1300L)
    println("job: I'm tired of waiting!")
    job.cancelAndJoin()
    println("done")
}