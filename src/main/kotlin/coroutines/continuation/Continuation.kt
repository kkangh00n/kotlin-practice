package coroutines.continuation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Continuation - 코루틴의 중단과 재개를 가능하게 하는 객체
 * - 코루틴이 중단된 지점부터 재개하기 위해 필요한 정보를 담고 있는 객체
 *
 * CPS(Continuation Passing Style)
 * - suspend 함수 컴파일 시 변환되는 스타일
 * - continuation 객체를 넘기며, 코루틴 중단과 재개를 반복
 */

fun main(): Unit = runBlocking {
    val userService = UserService()

    //1. 기존 suspend 함수
//    userService.findUserDefault(1)

    //2. CPS 변환 예시
    userService.findUserContinuation(1, null)

}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    /**
     * 기존 suspend 함수
     */
    suspend fun findUserDefault(userId: Long): UserDto {
        //suspend function -> 1차 중단
        println(" 유저를 가져오겠습니다")
        val profile = userProfileRepository.findProfile(userId)

        //suspend function -> 2차 중단
        println("이미지를 가져오겠습니다")
        val image = userImageRepository.findImage(profile)

        return UserDto(profile, image)
    }




    interface Continuation {
        suspend fun resumeWith(data: Any?)
    }

    private abstract class FindUserContinuation(val userId: Long) : Continuation {
        init {
            println("1. Continuation 생성")
        }
        var label = 0
        var profile: Profile? = null
        var image: Image? = null
    }

    /**
     * CPS 변환 예시
     * 1. Continuation 파라미터 추가 -> CPS 변환
     * 2. 중단 예상 지점을 switch문으로 분기, 각 분기마다 Continuation 객체를 넘겨줌
     *      -> 상태는 Heap 메모리에 저장, Continuation이 객체이기 때문
     *
     * 이점
     * 1. Continuation이 콜백을 자동으로 처리하고, 객체 내 변수를 통해서 코루틴 상태를 관리한다.
     * 2. 일반 동기 코드였다면 비동기 작업이 실행될 경우 스레드가 블로킹되어야 하지만, 코루틴에서는 CPS로 변환되어 코루틴을 중단하고 스레드를 해제한다.
     * 3. 해제된 스레드는 다른 코루틴이 사용할 수 있으며, 작업 완료 후 (같거나 다른) 스레드에서 코루틴이 재개된다.
     *    이를 통해 적은 수의 스레드로 수많은 코루틴을 효율적으로 실행할 수 있다.
     *      -> (중단된 코루틴이 재개되는 로직은 조금 더 봐야할 듯 하다..)
     *
     * 정리
     * Continuation을 활용한 cps 스타일의 변환은,
     * 하나의 suspend 함수 내에서 실제로 중단되는 지점(비동기 작업이 진행되는 지점)을 적은 스레드에서 적은 context switching 값으로 효율적으로 실행하기 위함
     */
    // Continuation 파라미터 추가
    suspend fun findUserContinuation(userId: Long, continuation: Continuation?): UserDto {

        val sm = continuation as? FindUserContinuation ?: object : FindUserContinuation(userId) {
            override suspend fun resumeWith(data: Any?) {
                when (super.label) {
                    0 -> {
                        println("3. resumeWith $label")
                        profile = data as Profile
                        label = 1
                    }
                    1 -> {
                        println("5. resumeWith $label")
                        image = data as Image
                        label = 2
                    }
                }

                println("재귀 함수 호출")
                findUserContinuation(userId, this)
            }
        }

        when (sm.label) {
            0 -> {
                println("2. resumeWith 0")
                println(" 유저를 가져오겠습니다")
                /**
                 * 1차 중단
                 * 비동기 작업 시작 -> 코루틴 중단, 스레드 해제
                 */
                val profile = userProfileRepository.findProfile(userId)

                /**
                 * 비동기 작업 완료 시, Continuation 재개
                 */
                sm.resumeWith(profile)
            }
            1 -> {
                println("4. resumeWith 1")
                println("이미지를 가져오겠습니다")
                /**
                 * 2차 중단
                 * 비동기 작업 시작 -> 코루틴 중단, 스레드 해제
                 */
                val image = userImageRepository.findImage(sm.profile!!)

                /**
                 * 비동기 작업 완료 시, Continuation 재개
                 */
                sm.resumeWith(image)
            }
        }
        return UserDto(sm.profile, sm.image)
    }
}

data class UserDto(
    val profile: Profile?,
    val image: Image?,
)

class UserProfileRepository {
    suspend fun findProfile(userId: Long): Profile {
        delay(100L)
        return Profile()
    }
}
class Profile
class UserImageRepository {
    suspend fun findImage(profile: Profile): Image {
        delay(100L)
        return Image()
    }
}
class Image