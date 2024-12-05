import kotlin.math.abs

private fun part1(ls: List<String>): Int {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()

    for (l in ls) {
        val (c, d) = ints(l)
        a.addLast(c)
        b.addLast(d)
    }

    a.sort()
    b.sort()

    return a.zip(b) { c, d -> abs(c - d) }.sum()
}

private fun part2(ls: List<String>): Int {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()

    for (l in ls) {
        val (c, d) = ints(l)
        a.addLast(c)
        b.addLast(d)
    }

    val bb = mutableMapOf<Int, Int>()
    for (d in b) {
        bb[d] = bb.getOrDefault(d, 0) + 1
    }

    return a.sumOf { c -> c * bb.getOrDefault(c, 0) }
}

fun main() {
    val ls = readInput()

//    println(part1(ls))
//    println(part2(ls))
}
