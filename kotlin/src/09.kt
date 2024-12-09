fun main() {
    val ls = readInput()

    fun part1(): Long {
        var ans = 0L

        val disk = ls[0].map { it.digitToInt() }.toMutableList()

        var i = 0
        var j = disk.size - (if (disk.size % 2 == 0) 2 else 1)
        var size = 0
        while (j > i) {
            if (i % 2 == 0) {
                while (disk[i] > 0) {
                    ans += size * (i / 2)
                    size++
                    disk[i]--
                }
                i++
            }
            else {
                while (disk[i] > 0 && disk[j] > 0) {
                    ans += size * (j / 2)
                    size++
                    disk[i]--
                    disk[j]--
                }

                if (disk[i] == 0)
                    i++
                if (disk[j] == 0)
                    j -= 2
            }
        }
        while (disk[i] > 0) {
            ans += size * (i / 2)
            size++
            disk[i]--
        }

        return ans
    }

    fun part2(): Long {
        class Node(
            var value: Int,
            var size: Int,
            var prev: Node? = null,
            var next: Node? = null,
        )

        fun mergeNodes(nodeA: Node, nodeB: Node) {
            nodeA.size += nodeB.size
            nodeA.next = nodeB.next
            nodeB.next?.prev = nodeA
        }

        var ans = 0L

        val disk = ls[0].map { it.digitToInt() }.iterator()

        var fileId = 0
        val head = Node(fileId++, disk.next())
        var current = head
        var isFile = false
        for (size in disk) {
            val node = Node(if (isFile) fileId++ else -1, size, current)
            current.next = node
            current = node
            isFile = !isFile
        }

        if (current.value == -1)
            current = current.prev!!

        while (current != head) {
            var potentialSwitch = head
            while (potentialSwitch != current && (potentialSwitch.value != -1 || potentialSwitch.size < current.size))
                potentialSwitch = potentialSwitch.next!!

            if (potentialSwitch != current) {
                if (potentialSwitch.size == current.size) {
                    potentialSwitch.value = current.value
                }
                else {
                    val node = Node(-1, potentialSwitch.size - current.size, potentialSwitch, potentialSwitch.next)
                    potentialSwitch.value = current.value
                    potentialSwitch.size = current.size
                    potentialSwitch.next?.prev = node
                    potentialSwitch.next = node
                }

                current.value = -1
                if (current.next != null && current.next!!.value == -1)
                    mergeNodes(current, current.next!!)
                if (current.prev != null && current.prev!!.value == -1)
                    mergeNodes(current.prev!!, current)
            }

            current = current.prev!!
            while(current.value == -1)
                current = current.prev!!
        }

        var size = 0
        var chunk: Node? = head
        while (chunk != null) {
            if (chunk.value != -1) {
                ans += (chunk.size) * (2 * size + chunk.size - 1) / 2L * chunk.value
            }
            size += chunk.size
            chunk = chunk.next
        }

        return ans
    }

    println(part1())
    println(part2())
}
