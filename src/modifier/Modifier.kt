package modifier

/**
 * 접근 제어자
 *
 * public -> 모든 곳에서 접근 가능
 * protected -> 선언된 클래스 또는 하위 클래스 접근 가능 (.kt 파일에서는 사용 불가능), Java에서 의미가 달라짐
 * internal -> 같은 모듈에서 접근 가능 (default 삭제)
 * private -> 선언된 클래스 내에서만 접근 가능
 */


/**
 * 생성자의 접근 제어자 부여
 */
class Cat internal constructor(
    /**
     * name -> internal getter
     */
    internal val name: String,
    /**
     * owner -> private getter/setter
     */
    private val owner: String,
    price: Int
) {

    /**
     * price -> private setter
     */
    var price = price
        get() = field + 5000
        private set
}

