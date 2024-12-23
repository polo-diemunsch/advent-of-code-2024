import kotlin.time.measureTimedValue

fun main() {
    val ls = readInput()
    val graph = mutableMapOf<String, MutableSet<String>>()
    for (l in ls) {
        val (a, b) = l.split('-')
        graph.getOrPut(a) { mutableSetOf() }.add(b)
        graph.getOrPut(b) { mutableSetOf() }.add(a)
    }

    fun part1(): Int {
        val vertices = graph.keys.sortedBy { it[0] != 't' }

        val k = 3
        val store = MutableList(k) { "" }

        fun getNbCliques(i: Int, l: Int): Int {
            if (l == k)
                return 1

            var result = 0
            store[l-1] = vertices[i]

            for (j in i + 1 .. vertices.lastIndex - (k - l - 1)) {
                if (graph[vertices[j]]!!.size >= k && graph[vertices[j]]!!.containsAll(store.take(l))) {
                    result += getNbCliques(j, l + 1)
                }
            }

            return result
        }

        return vertices.withIndex().takeWhile { it.index < vertices.size - (k - 1) && it.value[0] == 't' }.sumOf { getNbCliques(it.index, 1) }
    }

    fun part2(): String {
        var ansSize = 0
        var ans = ""

        val vertices = graph.keys.sorted()

        val store = MutableList(vertices.size) { "" }

        fun getMaxClique(i: Int, l: Int) {
            if (l > ansSize) {
                ansSize = l
                ans = store.take(l).joinToString(",")
            }

            for (j in i + 1 .. vertices.lastIndex) {
                if (graph[vertices[j]]!!.containsAll(store.take(l))) {
                    store[l] = vertices[j]
                    getMaxClique(j, l + 1)
                }
            }
        }

        getMaxClique(-1, 0)

        return ans
    }

    println(measureTimedValue { part1() }.let { "${it.value} (${it.duration})" })
    println(measureTimedValue { part2() }.let { "${it.value} (${it.duration})" })
}
