val arrayToSeconds: Array<String> = arrayOf("секунду", "секунды", "секунд")
val arrayToMinuts: Array<String> = arrayOf("минуту", "минуты", "минут")
val arrayToHours: Array<String> = arrayOf("час", "часа", "часов")
val arrayToDays: Array<String> = arrayOf("сутки", "суток", "суток")
val arrayWrong: Array<String> = arrayOf("Ошибка")
val seconds = 86402


fun main() {

    print(stringForCurrentQuality(convertSecToNum(seconds), agoToText(arrayToSeconds, arrayToMinuts, arrayToHours, arrayToDays)))

}

fun isNow(sec: Int): Boolean {
    return sec in 0..5
}

fun isYesterday(sec: Int): Boolean {
    return sec in (48 * 60 * 60 + 1)..(62 * 60 * 60)
}

fun isDay(sec: Int): Boolean {
    return sec in (24 * 60 * 60 + 1)..(48 * 60 * 60)
}

fun isHour(sec: Int): Boolean {
    return sec in (60 * 60)..(24 * 60 * 60)
}

fun isMin(sec: Int): Boolean {
    return sec in 60..60 * 60
}

fun isSec(sec: Int): Boolean {
    return sec in 5..59
}

fun compereToOne(sec: Int): Boolean {
    return sec in 51 downTo 21 step 10 || sec == 1
}

fun compereToTwoTillFour(sec: Int): Boolean {
    return sec in 54 downTo 24 step 10 || sec in 53 downTo 23 step 10 || sec in 52 downTo 22 step 10 || sec in 2..4
}

fun convertSecToNum(sec: Int): Int{
    val result = when {
        isSec(sec) -> sec
        isMin(sec) -> sec / 60
        isHour(sec) -> sec / 60 / 60
        isDay(sec) -> ( sec / 60 / 60 / 24)
        else -> 0
    }
    return result
}

fun agoToText(first: Array<String>, second: Array<String>, third: Array<String>, fourth: Array<String>): Array<String> {
    val result = when {

        isSec(seconds) -> first
        isMin(seconds) -> second
        isHour(seconds) -> third
        isDay(seconds) -> fourth
        else -> arrayWrong
    }
    return result
}

fun stringForCurrentQuality( sec: Int, first: Array<String> ): String{

    val result = when {
        isNow(seconds) -> "только что"
        compereToOne(sec) -> first[0] + " назад"
        isYesterday(sec) ->  "вчера"
        compereToTwoTillFour(sec) -> first[1] + " назад"
        else -> first[2] + " назад"
    }
    return result
}

fun print(condition: String) {

    println("был(а) ${convertSecToNum(seconds)} $condition")
}


