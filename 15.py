from aoc import *

DAY = 15

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = iter(s.splitlines())

grid = []
line = next(ls)
while line != "":
    grid.append(list(line))
    line = next(ls)

movements = ""
while line is not None:
    movements += line
    line = next(ls, None)

n = len(grid)
m = len(grid[0])
initial_pos = (-1, -1)
i = 1
while initial_pos == (-1, -1):
    j = 1
    while j < m - 1 and initial_pos == (-1, -1):
        if grid[i][j] == "@":
            initial_pos = (i, j)
            grid[i][j] = "."
        j += 1
    i += 1

movement_to_dir = {
    "^": DIR4[0],
    ">": DIR4[1],
    "v": DIR4[2],
    "<": DIR4[3],
}

def part1():
    ans = 0

    i, j = initial_pos
    for di, dj in map(lambda x: movement_to_dir[x], movements):
        ni, nj = i + di, j + dj
        mi, mj = ni, nj
        while grid[mi][mj] == "O":
            mi += di
            mj += dj
        if grid[mi][mj] == "#":
            continue
        if (ni, nj) != (mi, mj):
            grid[ni][nj] = "."
            grid[mi][mj] = "O"
        i, j = ni, nj

    for i in range(1, n - 1):
        for j in range(1, m - 1):
            if grid[i][j] == "O":
                ans += 100 * i + j

    return ans


def part2():
    ans = 0

    doubles = {"#": ["#", "#"], ".": [".", "."], "O": ["[", "]"]}
    doubled_grid = []
    for line in grid:
        doubled_grid.append([])
        for tile in line:
            doubled_grid[-1].extend(doubles[tile])

    i, j = initial_pos
    j *= 2
    for di, dj in map(lambda x: movement_to_dir[x], movements):
        if di == 0:
            nj = j + dj
            while doubled_grid[i][nj] not in (".", "#"):
                nj += dj
            if doubled_grid[i][nj] == "#":
                continue
            mj = nj - dj
            while nj != j:
                doubled_grid[i][nj] = doubled_grid[i][mj]
                nj = mj
                mj -= dj
            j += dj
        else:
            steps = []
            k = i
            current = {j}
            good = True
            while current and good:
                steps.append(current)
                k += di
                next_current = set()
                for l in current:
                    if doubled_grid[k][l] == "#":
                        good = False
                        break
                    if doubled_grid[k][l] in ("[", "]"):
                        next_current.add(l)
                        next_current.add(l + (1 if doubled_grid[k][l] == "[" else -1))
                current = next_current

            if not good:
                continue

            for step in reversed(steps[1:]):
                lk = k - di
                for l in step:
                    doubled_grid[k][l] = doubled_grid[lk][l]
                    doubled_grid[lk][l] = "."
                k -= di
            i += di

    for i in range(1, n - 1):
        for j in range(2, 2 * (m - 1)):
            if doubled_grid[i][j] == "[":
                ans += 100 * i + j

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
