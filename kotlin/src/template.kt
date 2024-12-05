private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" +".toRegex())
private fun readInts() = readStrings().map { it.toInt() }
private fun ints(s: String) = s.split(" +".toRegex()).map { it.toInt() }

private fun part1(ls: List<String>): Int {
    var ans = 0

//    for (l in ls)


    return ans
}

private fun part2(ls: List<String>): Int {
    var ans = 0

//    for (l in ls)


    return ans
}

fun main() {
    val ls = mutableListOf<String>()
    var l = readStr()
    while (l != "") {
        ls.addLast(l)
        l = readStr()
    }

    println(part1(ls))
//    println(part2(ls))
}
