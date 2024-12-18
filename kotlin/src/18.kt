import java.util.LinkedList
import java.util.Queue
import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()
    val corruptedCoordinates = ls.map { l -> l.split(',').map { c -> c.toInt() }.let { Pair(it[0], it[1]) } }
    val n = 70

    fun setTime(time: Int): List<CharArray> {
        val grid = List(n + 1) { CharArray(n + 1) { '.' } }
        for (t in 0 ..< time) {
            val coordinates = corruptedCoordinates[t]
            grid[coordinates.first][coordinates.second] = '#'
        }

        return grid
    }

    fun getLengthPath(grid: List<CharArray>): Int {
        val start = Pair(0, 0)
        val end = Pair(n, n)

        val q: Queue<Pair<Int, Pair<Int, Int>>> = LinkedList()
        q.add(Pair(0, start))
        val visited = mutableSetOf(start)
        while (q.isNotEmpty()) {
            val (length, current) = q.remove()

            for (neighbour in DIR4.map { current + it }) {
                if (neighbour.first in 0 .. n && neighbour.second in 0 .. n && grid[neighbour.first][neighbour.second] != '#' && neighbour !in visited) {
                    if (neighbour == end)
                        return length + 1
                    q.add(Pair(length + 1, neighbour))
                    visited.add(neighbour)
                }
            }
        }

        return -1
    }

    fun part1(): Int {
        return getLengthPath(setTime(1024))
    }

    fun part2(): String {
        return corruptedCoordinates.indices.toList().binarySearch { -getLengthPath(setTime(it)) }.let { corruptedCoordinates[-it-2] }.let { "${it.first},${it.second}" }
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
