from collections import defaultdict

from aoc import *

DAY = 10

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = [list(map(int, list(l))) for l in s.splitlines()]
n = len(ls)
m = len(ls[0])

def part1():
    ans = 0

    cache = {}
    def get_nb_trails(i, j):
        pos = (i, j)
        if pos not in cache:
            if ls[i][j] == 9:
                cache[pos] = {pos}
            else:
                trail_ends = set()
                for di, dj in DIR4:
                    ni = i + di
                    nj = j + dj
                    if 0 <= ni < n and 0 <= nj < m and ls[ni][nj] == ls[i][j] + 1:
                        trail_ends |= get_nb_trails(ni, nj)
                cache[pos] = trail_ends

        return cache[pos]

    for i in range(n):
        for j in range(m):
            if ls[i][j] == 0:
                ans += len(get_nb_trails(i, j))

    return ans


def part2():
    ans = 0

    cache = {}
    def get_nb_trails(i, j):
        pos = (i, j)
        if pos not in cache:
            if ls[i][j] == 9:
                cache[pos] = 1
            else:
                nb_trails = 0
                for di, dj in DIR4:
                    ni = i + di
                    nj = j + dj
                    if 0 <= ni < n and 0 <= nj < m and ls[ni][nj] == ls[i][j] + 1:
                        nb_trails += get_nb_trails(ni, nj)
                cache[pos] = nb_trails

        return cache[pos]

    for i in range(n):
        for j in range(m):
            if ls[i][j] == 0:
                ans += get_nb_trails(i, j)

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
