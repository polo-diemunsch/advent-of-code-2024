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

inline operator fun <reified T : Number>  Pair<T, T>.plus(other: Pair<T, T>): Pair<T, T> =
    when (T::class) {
        Int::class -> Pair((this.first.toInt() + other.first.toInt()) as T, (this.second.toInt() + other.second.toInt()) as T)
        Long::class -> Pair((this.first.toLong() + other.first.toLong()) as T, (this.second.toLong() + other.second.toLong()) as T)
        else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
    }

inline operator fun <reified T : Number>  Pair<T, T>.minus(other: Pair<T, T>): Pair<T, T> =
    when (T::class) {
        Int::class -> Pair((this.first.toInt() - other.first.toInt()) as T, (this.second.toInt() - other.second.toInt()) as T)
        Long::class -> Pair((this.first.toLong() - other.first.toLong()) as T, (this.second.toLong() - other.second.toLong()) as T)
        else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
    }

fun Int.length() = when(this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun Long.length() = when(this) {
    0L -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun <T> MutableMap<T, Int>.add(key: T, value: Int) {
    this[key] = this.getOrDefault(key, 0) + value
}

fun <T> List<T>.toCounter(): Map<T, Int> {
    val counter = mutableMapOf<T, Int>()
    for (value in this)
        counter.add(value, 1)
    return counter
}

fun <T> MutableMap<T, Long>.add(key: T, value: Long) {
    this[key] = this.getOrDefault(key, 0) + value
}

fun <T> List<T>.toLongCounter(): Map<T, Long> {
    val counter = mutableMapOf<T, Long>()
    for (value in this)
        counter.add(value, 1)
    return counter
}
