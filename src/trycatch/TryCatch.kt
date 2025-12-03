package trycatch

import java.io.BufferedReader
import java.io.FileReader

/**
 * try catch, use
 * - 코틀린에서 예외는 모두 Unchecked Exception
 * - 표현식으로 간주
 */
fun main() {
    println(parseIntOrThrow("3"))
    println(parseIntOrThrow("no"))

    readFile("example.txt")
}

/**
 * try catch
 */
fun parseIntOrThrow(str: String): Int? {
    return try {
        str.toInt()
    } catch (_: NumberFormatException) {
        null
    }
}

/**
 * use -> try resource
 * - 자원 사용 후 내부적으로 close() 호출
 */
fun readFile(path: String) {
    BufferedReader(FileReader(path)).use {
        reader -> println(reader.readLine())
    }
}