from aoc import *

DAY = 18

s = get_input(DAY)
#s = get_example(DAY)

import collections
import math

ls = s.splitlines()
corrupted_coordinates = [tuple(map(int, l.split(","))) for l in ls]

time = 0
n = 70
grid = [["." for _ in range(n + 1)] for __ in range(n + 1)]


def make_byte_fall():
    global time
    i, j = corrupted_coordinates[time]
    grid[i][j] = "#"
    time += 1


def get_length_path():
    q = collections.deque([(0, 0, 0)])
    visited = {(0, 0)}
    while q:
        c, i, j = q.popleft()

        for ni, nj in map(lambda d: (i + d[0], j + d[1]), DIR4):
            if 0 <= ni <= n and 0 <= nj <= n and grid[ni][nj] != "#" and (ni, nj) not in visited:
                if (ni, nj) == (n, n):
                    return c + 1
                q.append((c + 1, ni, nj))
                visited.add((ni, nj))

    return -1


def part1():
    for _ in range(1024):
        make_byte_fall()

    return get_length_path()


def part2():
    while get_length_path() != -1:
        make_byte_fall()

    return ",".join(map(str, corrupted_coordinates[time - 1]))


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
