fun main() {
    val ls = readInput()
    val n = ls.size
    val m = ls[0].length

    val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
    for (i in 0 ..< n) {
        for (j in 0 ..< m) {
            if (ls[i][j] != '.')
                antennas.getOrPut(ls[i][j]) { mutableListOf() }.add(Pair(i, j))
        }
    }

    fun part1(): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        for (positions in antennas.values) {
            if (positions.size == 1)
                continue
            for (i in 0 ..< positions.size) {
                for (j in i + 1 ..< positions.size) {
                    val delta = positions[i] - positions[j]
                    var pos = positions[i] + delta
                    if (pos.first in 0 ..< n && pos.second in 0 ..< m)
                        antinodes.add(pos)

                    pos = positions[j] - delta
                    if (pos.first in 0 ..< n && pos.second in 0 ..< m)
                        antinodes.add(pos)
                }
            }
        }

        return antinodes.size
    }

    fun part2(): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        for (positions in antennas.values) {
            if (positions.size == 1)
                continue
            for (i in 0 ..< positions.size) {
                antinodes.add(positions[i])
                for (j in i + 1 ..< positions.size) {
                    val delta = positions[i] - positions[j]
                    var pos = positions[i] + delta
                    while (pos.first in 0 ..< n && pos.second in 0 ..< m) {
                        antinodes.add(pos)
                        pos += delta
                    }

                    pos = positions[j] - delta
                    while (pos.first in 0 ..< n && pos.second in 0 ..< m) {
                        antinodes.add(pos)
                        pos -= delta
                    }
                }
            }
        }

        return antinodes.size
    }

    println(part1())
    println(part2())
}
