package delegation

/**
 * 위임 패턴
 * - 객체가 자신의 기능을 다른 객체에게 위임하여 기능을 실행하는 디자인 패턴
 *
 * 상속의 문제점
 * 1. 상위 클래스의 내용 변경 시 하위 클래스들에 영향이 감
 * 2. 불필요한 상위 클래스의 메서드까지 구현
 * 3. 전체적으로 상위 클래스에 대한 하위 클래스의 의존도가 높아짐
 *
 * 특정 기능을 직접 구현하는 대신, 다른 객체의 그 기능을 위임하여 코드의 중복을 줄임
 *
 */
interface SoundBehavior {
    fun makeSound()
}

class ScreamBehavior(val n: String) : SoundBehavior {
    override fun makeSound() = println("${n.uppercase()} !!!")
}

class RockAndRollBehavior(val n: String) : SoundBehavior {
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

/**
 * 기존 위임 패턴
 * - 로직을 위임받지만, 불필요하게 override 메서드를 구현해야 함.
 */
//class Singer(private val soundBehavior: SoundBehavior) : SoundBehavior {
//    override fun makeSound() {
//        soundBehavior.makeSound()
//    }
//}

/**
 * by 위임 패턴
 * - 로직을 위임받으며, 불필요한 override를 하지 않아도 되기에 보일러 플레이트 코드가 발생하지 않음.
 */
class Singer(soundBehavior: SoundBehavior) : SoundBehavior by soundBehavior

fun main() {
    val elvisPresley = Singer(RockAndRollBehavior("Dancin' to the Jailhouse Rock."))
    play(elvisPresley)

    val tomAraya = Singer(ScreamBehavior("Thrash Metal"))
    play(tomAraya)
}

/**
 * 다형성 적용 가능
 */
fun play(singer: SoundBehavior) {
    singer.makeSound()
}