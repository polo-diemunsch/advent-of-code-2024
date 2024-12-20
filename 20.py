from aoc import *

DAY = 20

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()
n = len(ls)
m = len(ls[0])
start_pos = find_pos(ls, "S")
end_pos = find_pos(ls, "E")


def solve(time, threshold=100):
    d = 0
    pos = start_pos
    dist = {start_pos: 0}
    while pos != end_pos:
        d += 1
        for next_pos in map(lambda x: (pos[0] + x[0], pos[1] + x[1]), DIR4):
            if next_pos not in dist and ls[next_pos[0]][next_pos[1]] != "#":
                dist[next_pos] = d
                pos = next_pos
                break

    ans = 0

    for pos in dist:
        for di in range(-time, time + 1):
            for dj in range(-time + abs(di), time - abs(di) + 1):
                next_pos = (pos[0] + di, pos[1] + dj)
                d = abs(di) + abs(dj)
                if next_pos in dist and dist[next_pos] - dist[pos] - d >= threshold:
                    ans += 1

    return ans


def part1():
    return solve(2)


def part2():
    return solve(20)


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
