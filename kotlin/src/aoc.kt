fun readStr() = readln()
fun readInt() = readStr().toInt()
fun readStrings() = readStr().split(" +".toRegex())
fun readInts() = readStrings().map { it.toInt() }
fun readInput() = generateSequence(::readStr).takeWhile { it.isNotEmpty() }.toList()
fun ints(s: String) = s.split(" +".toRegex()).map { it.toInt() }

val DIR4 = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Clockwise URDL
val DIR8 = listOf(Pair(-1, 0), Pair(-1, 1), Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(1, -1), Pair(0, -1), Pair(-1, -1)) // Clockwise from U
val DIRX = listOf(Pair(-1, 1), Pair(1, 1), Pair(1, -1), Pair(-1, -1)) // Clockwise from UL
