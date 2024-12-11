import kotlin.time.measureTimedValue
import kotlin.math.abs

fun main() {
    val ls = readInput()

    val locationIDsA = mutableListOf<Int>()
    val locationIDsB = mutableListOf<Int>()

    for (l in ls) {
        val (a, b) = ints(l)
        locationIDsA.addLast(a)
        locationIDsB.addLast(b)
    }

    fun part1(): Int {
        return locationIDsA.sorted().zip(locationIDsB.sorted()) { a, b -> abs(a - b) }.sum()
    }

    fun part2(): Int {
        val locationIDsBCounter = locationIDsB.toCounter()
        return locationIDsA.sumOf { a -> a * locationIDsBCounter.getOrDefault(a, 0) }
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
