import java.util.LinkedList
import java.util.Queue
import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()
    val n = ls.size
    val m = ls[0].length

    fun part1(): Int {
        var ans = 0

        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in 0 ..< n) {
            for (j in 0 ..< m) {
                val pos = Pair(i, j)
                if (pos in visited)
                    continue

                visited.add(pos)
                val q: Queue<Pair<Int, Int>> = LinkedList()
                q.add(pos)
                var area = 0
                var perimeter = 0
                while (q.isNotEmpty()) {
                    val current = q.remove()
                    area++
                    for (dir in DIR4) {
                        val neighbour = current + dir
                        if (
                            neighbour.first < 0 || neighbour.first >= n ||
                            neighbour.second < 0 || neighbour.second >= m ||
                            ls[neighbour.first][neighbour.second] != ls[current.first][current.second]
                        ) {
                            perimeter++
                        }
                        else if (neighbour !in visited) {
                            visited.add(neighbour)
                            q.add(neighbour)
                        }
                    }
                }
                ans += area * perimeter
            }
        }

        return ans
    }

    fun part2(): Int {
        var ans = 0

        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in 0 ..< n) {
            for (j in 0 ..< m) {
                val pos = Pair(i, j)
                if (pos in visited)
                    continue

                visited.add(pos)
                val q: Queue<Pair<Int, Int>> = LinkedList()
                q.add(pos)
                var area = 0
                var sides = 0
                val fences = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
                while (q.isNotEmpty()) {
                    val current = q.remove()
                    area++
                    for (k in DIR4.indices) {
                        val dir = DIR4[k]
                        val neighbour = current + dir
                        if (
                            neighbour.first < 0 || neighbour.first >= n ||
                            neighbour.second < 0 || neighbour.second >= m ||
                            ls[neighbour.first][neighbour.second] != ls[current.first][current.second]
                        ) {
                            when (listOf(DIR4[(k - 1 + DIR4.size) % DIR4.size], DIR4[(k + 1) % DIR4.size]).count { Pair(neighbour + it, dir) in fences }) {
                                0 -> sides++
                                2 -> sides--
                            }
                            fences.add(Pair(neighbour, dir))
                        }
                        else if (neighbour !in visited) {
                            visited.add(neighbour)
                            q.add(neighbour)
                        }
                    }
                }
                ans += area * sides
            }
        }

        return ans
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
