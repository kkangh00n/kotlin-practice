package companion

/**
 * companion object
 * - static 메모리에 할당
 */
fun main() {

//    Person("홍길동", 1)
    val person = Person.Factory.toObject()
    println(person)

}

class Person private constructor(
    var name: String,
    val age: Int
) {
    /**
     * 클래스와 동행하는 유일한 object
     * 객체이기 때문에 이름 지정, 상속, 구현 가능
     */
    companion object Factory {

        // static 변수
        // 컴파일 시 변수가 할당
        const val MIN_AGE = 1

        // 템플릿 메서드 패턴
        fun toObject(): Person {
            return Person(name = "홍길동", age = MIN_AGE)
        }
    }


}