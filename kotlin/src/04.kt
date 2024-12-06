fun main() {
    val ls = readInput()
    val n = ls.size
    val m = ls[0].length

    fun part1(): Int {
        var ans = 0

        val XMAS = "XMAS"

        for (i in 0..<n) {
            for (j in 0..<m) {
                ans += DIR8.count {
                    (di, dj) -> (0..<4).all {
                        k -> i + k * di in 0..<n && j + k * dj in 0..<m && ls[i + k * di][j + k * dj] == XMAS[k]
                    }
                }
            }
        }

        return ans
    }

    fun part2(): Int {
        var ans = 0

        val possibleOrders = listOf("MMSS", "SMMS", "SSMM", "MSSM")

        for (i in 1..<n - 1) {
            for (j in 1..<m - 1) {
                if (ls[i][j] == 'A' && DIRX.map { (di, dj) -> ls[i + di][j + dj] }.joinToString("") in possibleOrders)
                    ans++
            }
        }

        return ans
    }

    println(part1())
    println(part2())
}
