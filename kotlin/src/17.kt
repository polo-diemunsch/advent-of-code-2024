import kotlin.time.measureTimedValue

fun main() {
    val registers = readInput()
    val instructions = readInput()[0].substringAfter(": ").split(',').map { it.toInt() }

    fun runProgram(baseA: Long) = sequence {
        var a = baseA
        var b = 0L
        var c = 0L

        var i = 0
        while (i < instructions.size) {
            val operand = instructions[i + 1].toLong()
            val comboOperand = when (operand) {
                4L -> a
                5L -> b
                6L -> c
                else -> operand
            }

            when(instructions[i]) {
                0 -> a = a shr comboOperand.toInt()
                1 -> b = b xor operand
                2 -> b = comboOperand % 8
                3 -> if (a != 0L) {
                    i = operand.toInt()
                    continue
                }
                4 -> b = b xor c
                5 -> yield((comboOperand % 8).toInt())
                6 -> b = a shr comboOperand.toInt()
                7 -> c = a shr comboOperand.toInt()
            }

            i += 2
        }
    }

    fun part1(): String {
        val a = registers[0].substringAfter(": ").toLong()
        return runProgram(a).joinToString(",")
    }

    fun part2(a: Long = 0L, i: Int = instructions.lastIndex): Long {
        if (i < 0)
            return a

        val currentA = a * 8
        for (j in 0 ..< 8) {
            val programOutput = runProgram(currentA + j).iterator()
            if ((i .. instructions.lastIndex).all { instructions[it] == programOutput.next() }) {
                val ans = part2(currentA + j, i - 1)
                if (ans > 0)
                    return ans
            }
        }

        return -1
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
