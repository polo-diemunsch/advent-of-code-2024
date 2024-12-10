import kotlin.time.measureTimedValue
import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val ls = readInput()

    fun part1(): Int {
        var ans = 0

        for (l in ls) {
            val report = ints(l)
            val diffs = report.zipWithNext { a, b -> b - a }
            if (diffs.all { diff -> diff.sign == diffs[0].sign && abs(diff) in 1..3 })
                ans++
        }

        return ans
    }

    fun part2(): Int {
        var ans = 0

        fun isGood(isIncreasing: Boolean, diff: Int) =
            (isIncreasing && diff in 1..3) || (!isIncreasing && diff in -3..-1)

        for (l in ls) {
            val report = ints(l)
            if (report.size <= 2) {
                ans++
                continue
            }

            val q: Queue<Triple<Boolean, Int, Boolean>> = LinkedList()
            var diff: Int

            diff = report[1] - report[0]
            if (abs(diff) in 1..3)
                q.add(Triple(diff > 0, 2, false))

            diff = report[2] - report[0]
            if (abs(diff) in 1..3)
                q.add(Triple(diff > 0, 3, true))

            diff = report[2] - report[1]
            if (abs(diff) in 1..3)
                q.add(Triple(diff > 0, 3, true))

            while (q.size > 0) {
                val (isIncreasing, i, skipped) = q.remove()

                if (i >= report.size || i == report.size - 1 && !skipped) {
                    ans++
                    break
                }

                diff = report[i] - report[i - 1]
                if (isGood(isIncreasing, diff)) {
                    q.add(Triple(isIncreasing, i + 1, skipped))
                }
                else if (!skipped) {
                    diff = report[i + 1] - report[i - 1]
                    if (isGood(isIncreasing, diff))
                        q.add(Triple(isIncreasing, i + 2, true))
                }
            }
        }

        return ans
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
