import java.util.LinkedList
import java.util.Queue
import kotlin.math.abs
import kotlin.math.sign

private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" +".toRegex())
private fun readInts() = readStrings().map { it.toInt() }
private fun ints(s: String) = s.split(" +".toRegex()).map { it.toInt() }

private fun part1(ls: List<String>): Int {
    var ans = 0

    for (l in ls) {
        val report = ints(l)
        val diffs = (1..<report.size).map { i -> report[i] - report [i - 1] }
        if (diffs.all { diff -> diff.sign == diffs[0].sign && abs(diff) in 1..3 })
            ans++
    }

    return ans
}

private fun part2(ls: List<String>): Int {
    var ans = 0

    fun isGood(isIncreasing: Boolean, diff: Int) = (isIncreasing && diff in 1..3) || (!isIncreasing && diff in -3..-1)

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

fun main() {
    val ls = mutableListOf<String>()
    var l = readStr()
    while (l != "") {
        ls.addLast(l)
        l = readStr()
    }

//    println(part1(ls))
//    println(part2(ls))
}
