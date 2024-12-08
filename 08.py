from collections import defaultdict

from aoc import *

DAY = 8

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()
n = len(ls)
m = len(ls[0])

antennas = defaultdict(list)
for i in range(n):
    for j in range(m):
        if ls[i][j] != '.':
            antennas[ls[i][j]].append((i, j))


def part1():
    antidotes = set()
    for positions in antennas.values():
        for k in range(len(positions)):
            pos1 = positions[k]
            for l in range(k + 1, len(positions)):
                pos2 = positions[l]
                di = pos1[0] - pos2[0]
                dj = pos1[1] - pos2[1]
                i, j = pos1[0] + di, pos1[1] + dj
                if 0 <= i < n and 0 <= j < m:
                    antidotes.add((i, j))
                i, j = pos2[0] - di, pos2[1] - dj
                if 0 <= i < n and 0 <= j < m:
                    antidotes.add((i, j))

    return len(antidotes)


def part2():
    antidotes = set()
    for positions in antennas.values():
        if len(positions) == 1:
            continue
        for k in range(len(positions)):
            pos1 = positions[k]
            antidotes.add(pos1)
            for l in range(k + 1, len(positions)):
                pos2 = positions[l]
                di = pos1[0] - pos2[0]
                dj = pos1[1] - pos2[1]
                i, j = pos1[0] + di, pos1[1] + dj
                while 0 <= i < n and 0 <= j < m:
                    antidotes.add((i, j))
                    i += di
                    j += dj
                i, j = pos2[0] - di, pos2[1] - dj
                while 0 <= i < n and 0 <= j < m:
                    antidotes.add((i, j))
                    i -= di
                    j -= dj

    return len(antidotes)


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
