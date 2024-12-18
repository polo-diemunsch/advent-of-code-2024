import kotlin.time.measureTimedValue

fun main() {
    val rules = readInput()
        .map { Pair(it.substringBefore('|').toInt(), it.substringAfter('|').toInt()) }
        .toSet()
    val updates = readInput().map { it.split(',').map { it.toInt() } }

    val comparator = Comparator { a: Int, b: Int -> (Pair(a, b) in rules).toInt() - (Pair(b, a) in rules).toInt() }

    fun isSorted(update: List<Int>) = update.zipWithNext { a, b -> comparator.compare(a, b) }.all { it >= 0 }

    fun part1(): Int {
        return updates.filter { isSorted(it) }.sumOf { it[it.size / 2] }
    }

    fun part2(): Int {
        return updates.filter { !isSorted(it) }.map { it.sortedWith(comparator) }.sumOf { it[it.size / 2] }
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
