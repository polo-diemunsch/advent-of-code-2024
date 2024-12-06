from aoc import *

DAY = 6

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = [list(line) for line in s.splitlines()]
n, m = len(ls), len(ls[0])


def find_start():
    start = None
    i = 0
    while i < n and start is None:
        j = 0
        while j < m and start is None:
            if ls[i][j] == "^":
                start = i, j
            j += 1
        i += 1

    return start


start = find_start()


def in_a_loop(pos, d):
    i, j = pos

    ni = i + DIR4[d][0]
    nj = j + DIR4[d][1]

    visited = {(i, j, d)}
    while 0 <= ni < n and 0 <= nj < m:
        if ls[ni][nj] == "#":
            d = (d + 1) % 4
        else:
            i, j = ni, nj
            v = (i, j, d)
            if v in visited:
                return True
            visited.add(v)

        ni = i + DIR4[d][0]
        nj = j + DIR4[d][1]

    return False


def part1():
    i, j = start
    d = 0

    ni = i + DIR4[d][0]
    nj = j + DIR4[d][1]

    visited = {(i, j)}
    while 0 <= ni < n and 0 <= nj < m:
        if ls[ni][nj] == "#":
            d = (d + 1) % 4
        else:
            i, j = ni, nj
            visited.add((i, j))

        ni = i + DIR4[d][0]
        nj = j + DIR4[d][1]

    return len(visited)


def part2():
    ans = 0

    i, j = start
    d = 0
    ni = i + DIR4[d][0]
    nj = j + DIR4[d][1]

    tested = set()
    while 0 <= ni < n and 0 <= nj < m:
        if ls[ni][nj] == "#":
            d = (d + 1) % 4
        else:
            if (ni, nj) not in tested:
                ls[ni][nj] = "#"
                ans += in_a_loop((i, j), d)
                ls[ni][nj] = "."
                tested.add((ni, nj))
            i, j = ni, nj

        ni = i + DIR4[d][0]
        nj = j + DIR4[d][1]

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
