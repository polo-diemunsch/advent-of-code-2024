fun main() {
    val ls = readInput().map { it.toCharArray() }
    val n = ls.size
    val m = ls[0].size

    val line = ls.indexOfFirst { it.contains('^') }
    val column = ls[line].indexOf('^')

    fun part1(): Int {
        var pos = Pair(line, column)
        var d = 0
        var nextPos = pos + DIR4[d]

        val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(pos)
        while (nextPos.first in 0..<n && nextPos.second in 0..<m) {
            if (ls[nextPos.first][nextPos.second] == '#') {
                d = (d + 1) % DIR4.size
            }
            else {
                pos = nextPos
                visited.add(pos)
            }

            nextPos = pos + DIR4[d]
        }

        return visited.size
    }

    fun part2(): Int {
        var ans = 0

        var pos = Pair(line, column)
        var d = 0
        var nextPos = pos - DIR4[d]
        var sidePos = pos + DIR4[(d + DIR4.size - 1) % DIR4.size]

        // Backward
        val visited: MutableSet<Pair<Pair<Int, Int>, Int>> = mutableSetOf(Pair(pos, d))
        while (nextPos.first in 0..<n && nextPos.second in 0..<m) {
            if (ls[sidePos.first][sidePos.second] == '#') {
                d = (d + DIR4.size - 1) % DIR4.size
            }
            else if (ls[nextPos.first][nextPos.second] == '#') {
                break
            }
            else {
                pos = nextPos
                visited.add(Pair(pos, d))
            }

            nextPos = pos - DIR4[d]
            sidePos = pos + DIR4[(d + DIR4.size - 1) % DIR4.size]
        }

        fun isInLoop(initialPos: Pair<Int, Int>, initialDirection: Int): Boolean {
            var pos = initialPos
            var d = initialDirection
            var nextPos = pos + DIR4[d]

            val newlyVisited: MutableSet<Pair<Pair<Int, Int>, Int>> = mutableSetOf(Pair(pos, d))
            while (nextPos.first in 0..<n && nextPos.second in 0..<m) {
                if (ls[nextPos.first][nextPos.second] == '#') {
                    d = (d + 1) % DIR4.size
                } else {
                    pos = nextPos
                    val p = Pair(pos, d)
                    if (p in visited || p in newlyVisited)
                        return true
                    newlyVisited.add(p)
                }

                nextPos = pos + DIR4[d]
            }

            return false
        }

        pos = Pair(line, column)
        d = 0
        nextPos = pos + DIR4[d]

        // Forward
        val tested: MutableSet<Pair<Int, Int>> = mutableSetOf(pos)
        while (nextPos.first in 0..<n && nextPos.second in 0..<m) {
            if (ls[nextPos.first][nextPos.second] == '#') {
                d = (d + 1) % DIR4.size
            }
            else {
                if (nextPos !in tested) {
                    ls[nextPos.first][nextPos.second] = '#'
                    if (isInLoop(pos, d))
                        ans++
                    ls[nextPos.first][nextPos.second] = '.'
                    tested.add(nextPos)
                }
                pos = nextPos
                visited.add(Pair(pos, d))
            }

            nextPos = pos + DIR4[d]
        }

        return ans
    }

    println(part1())
    println(part2())
}
