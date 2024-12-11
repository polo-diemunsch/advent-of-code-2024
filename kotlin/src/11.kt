import kotlin.math.pow
import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()

    fun blink(n: Int): Long {
        var stones = longs(ls[0]).toLongCounter()

        for (i in 0 ..< n) {
            val newStones = mutableMapOf<Long, Long>()
            for ((stone, nb) in stones.entries) {
                when {
                    stone == 0L -> newStones.add(1, nb)
                    stone.length() % 2 == 0 -> {
                        val pow10 = 10.0.pow(stone.length() / 2).toLong()
                        newStones.add(stone / pow10, nb)
                        newStones.add(stone % pow10, nb)
                    }
                    else -> newStones.add(stone * 2024, nb)
                }
            }
            stones = newStones
        }

        return stones.values.sum()
    }

    fun part1(): Long {
        return blink(25)
    }

    fun part2(): Long {
        return blink(75)
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
