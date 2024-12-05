from aoc import *

DAY = 4

s = get_input(DAY)
# s = get_example(DAY, 1)

import collections
import math

ls = s.splitlines()


def rotations(grid):
    n, m = len(grid), len(grid[0])

    yield grid

    quarter_right = ["" for _ in range(n + m - 1)]
    for i in range(n - 1, -1, -1):
        for j in range(n - i):
            quarter_right[i + j] += grid[i][j]
    for i in range(n - 1, -1, -1):
        for j in range(n - i, m):
            quarter_right[i + j] += grid[i][j]

    yield quarter_right

    quarter_left = ["" for _ in range(n + m - 1)]
    for i in range(m):
        for j in range(m - 1, i - 1, -1):
            quarter_left[i + (m - j - 1)] += grid[i][j]
    for i in range(1, n):
        for j in range(i):
            quarter_left[i + (m - j - 1)] += grid[i][j]

    yield quarter_left

    half_right = ["" for _ in range(m)]
    for j in range(m):
        for i in range(n - 1, -1, -1):
            half_right[j] += grid[i][j]

    yield half_right

def part1():
    ans = 0

    for rotation in rotations(ls):
        for line in rotation:
            ans += line.count("XMAS")
            ans += line.count("SAMX")

    return ans


def part2():
    ans = 0

    n, m = len(ls), len(ls[0])

    for i in range(1, n - 1):
        for j in range(1, m - 1):
            if ls[i][j] == "A":
                x = ""
                for di, dj in DIR8[1::2]:
                    x += ls[i + di][j + dj]
                if x in ("MMSS", "SMMS", "SSMM", "MSSM"):
                    ans += 1

    return ans


# submit(DAY, part1(), 1)
submit(DAY, part2(), 2)
