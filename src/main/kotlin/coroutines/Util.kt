package coroutines

fun printWithThread(str: Any?) {
    println("[${Thread.currentThread().name}] $str")
}