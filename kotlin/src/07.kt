import kotlin.math.pow

fun main() {
    val ls = readInput()
    val equations = ls.map { Pair(it.substringBefore(':').toLong(), longs(it.substringAfter(": "))) }

    fun part1(): Long {
        var ans: Long = 0

        for ((target, numbers) in equations) {
            var currentNumbers: Set<Long> = setOf(target)
            var i = numbers.size - 1
            while (i > 0 && currentNumbers.isNotEmpty()) {
                val nextCurrentNumbers: MutableSet<Long> = mutableSetOf()
                for (current in currentNumbers) {
                    if (current - numbers[i] >= 0)
                        nextCurrentNumbers.add(current - numbers[i])
                    if (current % numbers[i] == 0L)
                        nextCurrentNumbers.add(current / numbers[i])
                }
                currentNumbers = nextCurrentNumbers
                i--
            }

            if (numbers[0] in currentNumbers)
                ans += target
        }

        return ans
    }

    fun part2(): Long {
        var ans: Long = 0

        for ((target, numbers) in equations) {
            var currentNumbers: Set<Long> = setOf(target)
            var i = numbers.size - 1
            while (i > 0 && currentNumbers.isNotEmpty()) {
                val nextCurrentNumbers: MutableSet<Long> = mutableSetOf()
                for (current in currentNumbers) {
                    if (current - numbers[i] >= 0)
                        nextCurrentNumbers.add(current - numbers[i])
                    if (current % numbers[i] == 0L)
                        nextCurrentNumbers.add(current / numbers[i])
                    if (current % 10.0.pow(numbers[i].length()).toLong() == numbers[i])
                        nextCurrentNumbers.add(current / 10.0.pow(numbers[i].length()).toLong())
                }
                currentNumbers = nextCurrentNumbers
                i--
            }

            if (numbers[0] in currentNumbers)
                ans += target
        }

        return ans
    }

    println(part1())
    println(part2())
}
