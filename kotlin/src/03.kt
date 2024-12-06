fun main() {
    val ls = readInput()

    fun part1(): Int {
        var ans = 0

        val pattern = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")

        for (l in ls) {
            for (match in pattern.findAll(l)) {
                val (_, a, b) = match.groupValues
                ans += a.toInt() * b.toInt()
            }
        }

        return ans
    }

    fun part2(): Int {
        var ans = 0

        val pattern = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))|(don't\\(\\))")

        var mult = true
        for (l in ls) {
            for (match in pattern.findAll(l)) {
                val (_, a, b, do_, dont) = match.groupValues
                if (do_ != "")
                    mult = true
                else if (dont != "")
                    mult = false
                else if (mult)
                    ans += a.toInt() * b.toInt()
            }
        }

        return ans
    }

    println(part1())
    println(part2())
}
