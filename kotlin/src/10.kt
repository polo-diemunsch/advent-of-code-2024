import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()
    val n = ls.size
    val m = ls[0].length

    fun part1(): Int {
        var ans = 0

        val cache = mutableMapOf<Pair<Int, Int>, Set<Pair<Int, Int>>>()
        fun getNbTrails(pos: Pair<Int, Int>): Set<Pair<Int, Int>> {
            if (pos !in cache) {
                if (ls[pos.first][pos.second] == '9') {
                    cache[pos] = setOf(pos)
                }
                else {
                    val trailEnds = mutableSetOf<Pair<Int, Int>>()
                    for (dir in DIR4) {
                        val nPos = pos + dir
                        if (nPos.first in 0 ..< n && nPos.second in 0 ..< m && ls[nPos.first][nPos.second] == ls[pos.first][pos.second] + 1)
                            trailEnds.addAll(getNbTrails(nPos))
                    }
                    cache[pos] = trailEnds
                }
            }

            return cache[pos]!!
        }

        for (i in 0 ..< n) {
            for (j in 0 ..< m) {
                if (ls[i][j] == '0')
                    ans += getNbTrails(Pair(i, j)).size
            }
        }

        return ans
    }

    fun part2(): Int {
        var ans = 0

        val cache = mutableMapOf<Pair<Int, Int>, Int>()
        fun getNbTrails(pos: Pair<Int, Int>): Int {
            if (pos !in cache) {
                if (ls[pos.first][pos.second] == '9') {
                    cache[pos] = 1
                }
                else {
                    var nbTrails = 0
                    for (dir in DIR4) {
                        val nPos = pos + dir
                        if (nPos.first in 0 ..< n && nPos.second in 0 ..< m && ls[nPos.first][nPos.second] == ls[pos.first][pos.second] + 1)
                            nbTrails += getNbTrails(nPos)
                    }
                    cache[pos] = nbTrails
                }
            }

            return cache[pos]!!
        }

        for (i in 0 ..< n) {
            for (j in 0 ..< m) {
                if (ls[i][j] == '0')
                    ans += getNbTrails(Pair(i, j))
            }
        }

        return ans
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
