import kotlin.math.abs
import kotlin.math.log10

fun readStr() = readln()
fun readInt() = readStr().toInt()
fun readStrings() = readStr().split(" +".toRegex())
fun readInts() = readStrings().map { it.toInt() }
fun readInput() = generateSequence(::readStr).takeWhile { it.isNotEmpty() }.toList()
fun ints(s: String) = s.split(" +".toRegex()).map { it.toInt() }
fun longs(s: String) = s.split(" +".toRegex()).map { it.toLong() }

val DIR4 = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Clockwise URDL
val DIR8 = listOf(Pair(-1, 0), Pair(-1, 1), Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(1, -1), Pair(0, -1), Pair(-1, -1)) // Clockwise from U
val DIRX = listOf(Pair(-1, 1), Pair(1, 1), Pair(1, -1), Pair(-1, -1)) // Clockwise from UL

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first - other.first, this.second - other.second)
}

fun Int.length() = when(this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun Long.length() = when(this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun List<Any>.counter(): Map<Any, Int> {
    val counter = mutableMapOf<Any, Int>()
    for (value in this)
        counter[value] = counter.getOrDefault(value, 0) + 1
    return counter
}
