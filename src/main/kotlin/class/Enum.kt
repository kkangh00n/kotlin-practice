package `class`

/**
 * enum class
 */
enum class Country(
    val code: String
) {
    KOREA("KO"),
    AMERICA("US")
}

fun handleCountry(country: Country) {
    when (country) {

        /**
         * 모든 타입을 강제 체크
         * 모든 타입을 명시할 수 없다면 else절로 추가
         */
        Country.KOREA -> println("코리아")
        Country.AMERICA -> println("미국")
    }
}