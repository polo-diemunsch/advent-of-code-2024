import kotlin.time.measureTimedValue

fun main() {
    val clawMachines = mutableListOf<List<String>>()
    var ls = readInput()
    while (ls.isNotEmpty()) {
        clawMachines.add(ls)
        ls = readInput()
    }

    fun parseButton(line: String): Pair<Long, Long> {
        val pattern = Regex("^Button [A-Z]: X\\+(\\d+), Y\\+(\\d+)$")
        val match = pattern.find(line)
        if (match != null) {
            val (_, x, y) = match.groupValues
            return Pair(x.toLong(), y.toLong())
        }
        return Pair(0, 0)
    }

    fun parsePrize(line: String): Pair<Long, Long> {
        val pattern = Regex("^Prize: X=(\\d+), Y=(\\d+)$")
        val match = pattern.find(line)
        if (match != null) {
            val (_, x, y) = match.groupValues
            return Pair(x.toLong(), y.toLong())
        }
        return Pair(0, 0)
    }

    fun nbTokens(buttonA: Pair<Long, Long>, buttonB: Pair<Long, Long>, prize: Pair<Long, Long>): Long {
        val det = buttonA.first * buttonB.second - buttonA.second * buttonB.first
        if (det == 0L)
            return 0

        val x = buttonB.second * prize.first - buttonB.first * prize.second
        if (x % det != 0L)
            return 0
        val a = x / det

        val y = - buttonA.second * prize.first + buttonA.first * prize.second
        if (y % det != 0L)
            return 0
        val b = y / det

        if (a < 0 || b < 0)
            return 0

        return 3 * a + b
    }

    fun part1(): Long {
        var ans = 0L

        for (clawMachine in clawMachines) {
            val buttonA = parseButton(clawMachine[0])
            val buttonB = parseButton(clawMachine[1])
            val prize = parsePrize(clawMachine[2])

            ans += nbTokens(buttonA, buttonB, prize)
        }

        return ans
    }

    fun part2(): Long {
        var ans = 0L

        for (clawMachine in clawMachines) {
            val buttonA = parseButton(clawMachine[0])
            val buttonB = parseButton(clawMachine[1])
            val prize = parsePrize(clawMachine[2]) + Pair(10000000000000, 10000000000000)

            ans += nbTokens(buttonA, buttonB, prize)
        }

        return ans
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
