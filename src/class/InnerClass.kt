package `class`

/**
 * 내부 클래스
 * 주의 -> 내부 클래스를 만들 때는 외부 클래스를 참조 하지 않거나, 되도록 static 사용 권장
 * 이유 -> 내부 클래스는 숨겨진 외부 클래스 정보를 갖고 있기에, 참조를 하지 못하면 메모리 누수 발생 가능
 */
fun main() {

    //1. 외부 참조가 없는 내부 클래스 생성
    val livingRoom = House.LivingRoom(34.5)
    //2. 외부 클래스 생성
    val house = House("서울시 구로구", livingRoom)
    //3. 내부 클래스 생성
    val innerLivingRoom = house.InnerLivingRoom(34.5)

}

class House(
    val address: String,
    val livingRoom: LivingRoom
) {
    /**
     * 외부 참조가 없는 내부 클래스
     */
    class LivingRoom(
        private val area: Double
    )

    /**
     * 외부 참조가 존재하는 내부 클래스
     */
    inner class InnerLivingRoom(
        private val area: Double,
    ) {
        val address: String
            /**
             * 외부 클래스 참조
             */
            get() = this@House.address
    }

}