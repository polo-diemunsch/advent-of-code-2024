private fun part1(ls: List<String>): Int {
    var ans = 0

    val n = ls.size
    val m = ls[0].length

    val XMAS = "XMAS"

    for (i in 0..<n) {
        for (j in 0..<m) {
            ans += DIR8.count { (di, dj) -> (0..<4).all { k -> i + k * di in 0..<n && j + k * dj in 0..<m && ls[i + k * di][j + k * dj] == XMAS[k] } }
        }
    }

    return ans
}

private fun part2(ls: List<String>): Int {
    var ans = 0

    val n = ls.size
    val m = ls[0].length

    val possibleOrders = listOf("MMSS", "SMMS", "SSMM", "MSSM")

    for (i in 1..<n - 1) {
        for (j in 1..<m - 1) {
            if (ls[i][j] == 'A' && DIRX.map { (di, dj) -> ls[i + di][j + dj] }.joinToString("") in possibleOrders)
                ans++
        }
    }

    return ans
}

fun main() {
    val ls = readInput()

//    println(part1(ls))
//    println(part2(ls))
}
