import kotlin.math.pow
import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()

    val pattern = Regex("^p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)$")
    val robots = ls.map {
        val values = pattern.find(it)!!.groupValues
        Pair(Pair(values[1].toInt(), values[2].toInt()), Pair(values[3].toInt(), values[4].toInt()))
    }
    val n = 101
    val m = 103

    fun part1(): Int {
        val ans = MutableList(4) { 0 }

        val time = 100
        for ((initialPosition, speed) in robots) {
            val finalPosition = Pair(
                (initialPosition.first + time * speed.first).mod(n),
                (initialPosition.second + time * speed.second).mod(m),
            )
            if (finalPosition.first != n / 2 && finalPosition.second != m / 2) {
                ans[2 * (finalPosition.first > n / 2).toInt() + (finalPosition.second > m / 2).toInt()]++
            }
        }

        return ans.reduce { acc, i -> acc * i }
    }

    fun part2(): Int {
        fun findMinVarianceTime(
            initialPositions: List<Int>,
            speeds: List<Int>,
            limit: Int
        ): Int {
            val positions = initialPositions.toMutableList()
            var minVariance = Double.POSITIVE_INFINITY
            var minVarianceTime = 0

            for (time in 0 ..< limit) {
                val mean = positions.average()
                val variance = positions.map { (it - mean).pow(2) }.average()
                if (variance < minVariance) {
                    minVariance = variance
                    minVarianceTime = time
                }
                positions.indices.map { positions[it] = (positions[it] + speeds[it]).mod(limit) }
            }

            return minVarianceTime
        }

        val timeX = findMinVarianceTime(robots.map { it.first.first }, robots.map { it.second.first }, n)
        val timeY = findMinVarianceTime(robots.map { it.first.second }, robots.map { it.second.second }, m)

        return timeX + ((n.pow(m - 2, m) * (timeY - timeX)) % m) * n
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
