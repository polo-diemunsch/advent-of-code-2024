from aoc import *

DAY = 14

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import re

ls = s.splitlines()

robots = []
pattern = r"^p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)$"
for l in ls:
    match = re.search(pattern, l)
    robots.append(tuple(map(int, (match.group(i) for i in range(1, 5)))))

n, m = 101, 103


def part1():
    ans = [0, 0, 0, 0]
    time = 100

    for xi, yi, vx, vy in robots:
        x = (xi + time * vx) % n
        y = (yi + time * vy) % m
        if x < n // 2:
            if y < m // 2:
                ans[0] += 1
            elif y > m // 2:
                ans[1] += 1
        elif x > n // 2:
            if y < m // 2:
                ans[2] += 1
            elif y > m // 2:
                ans[3] += 1

    return math.prod(ans)


def part2():
    ans = 0

    positions = [(xi, yi) for xi, yi, *_ in robots]
    while True:
        ans += 1
        for i in range(len(robots)):
            vx, vy = robots[i][2:]
            x = (positions[i][0] + vx) % n
            y = (positions[i][1] + vy) % m
            positions[i] = (x, y)

        unique_positions = set(positions)
        seen = set()
        max_adjacent = 0
        for x, y in unique_positions:
            if (x, y) in seen:
                continue

            seen.add((x, y))
            q = collections.deque([(x, y)])
            count = 0
            while q:
                z, t = q.popleft()
                count += 1
                for n_pos in map(lambda d: (z + d[0], t + d[1]), DIR4):
                    if n_pos in unique_positions and n_pos not in seen:
                        seen.add(n_pos)
                        q.append(n_pos)

            if count > max_adjacent:
                max_adjacent = count

        if max_adjacent > 100:
            grid = [[" " for _ in range(n)] for __ in range(m)]
            for z, t in unique_positions:
                grid[t][z] = "#"

            for line in grid:
                print(*line, sep="")

            if input().lower().startswith("y"):
                return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
