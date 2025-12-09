package `class`

/**
 * 클래스
 */
fun main() {
    val person1 = Person("나")
    val person2 = Person("abc", 20)
    val person3 = Person(age = 30, name = "홍길동")

    //메서드와 custom getter 객체 접근 차이
    person1.plusAge()
    person1.name
}

class Person(
    /**
     * 생성자 및 필드
     * - default parameter를 통해 생성자 오버로딩 및 builder 역할 수행
     * - getter, setter 자동 생성
     */
    name: String = "ㅎㅎㅎ",
    var age: Int = 1
) {

    /**
     * 생성자에 포함되지 않는 필드
     */
    var id: Long? = null

    /**
     * init 블록
     * - 인스턴스 생성되는 시점에 호출
     */
    init {
        if (age<0) {
            throw RuntimeException()
        }
    }

    /**
     * 메서드 정의
     */
    fun plusAge() {
        this.age++
    }

    /**
     * backing field -> 무한 루프 방지
     * - custom getter명이 기존 필드 이름과 같을 때
     * - field -> 기존 필드 지칭!
     * - 만약 field가 아닌 name으로 작성하게 될 경우 -> 무한 루프 발생
     * - 결과적으로 name에 접근할 경우 name에 대한 uppercase() 반환
     */
    var name: String = name
        /**
         * custom getter
         */
        get() = field.uppercase()
        /**
         * private setter
         */
        private set


    /**
     * 메서드 오버라이드
     */
    override fun equals(other: Any?): Boolean {
        return other is Person && other.name == this.name
    }

}
