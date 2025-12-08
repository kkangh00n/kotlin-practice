package extension

fun main() {

    //확장 함수 호출
    println("ABC".lastChar())

    //확장 프로퍼티 호출
    println("ABC".lastChar)
}

/**
 * 확장 함수
 * - 기존 클래스 혹은 라이브러리의 외부에서 작성된 메서드이지만 멤버 메서드인 것처럼 호출 가능
 * - Java 호환 용이
 * - 오버라이드는 불가
 */
fun String.lastChar(): Char {
    return this[this.length-1]
}

/**
 * 확장 프로퍼티
 * - 최소한 getter는 정의를 해야 함
 * - 초기화 코드를 쓸 수 없음.
 */
val String.lastChar: Char
    get() = get(length - 1)