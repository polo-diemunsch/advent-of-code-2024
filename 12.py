from aoc import *

DAY = 12

s = get_input(DAY)
# s = get_example(DAY, 6)

import collections
import math

ls = s.splitlines()
n = len(ls)
m = len(ls[0])

def part1():
    ans = 0

    visited = set()
    for i in range(n):
        for j in range(m):
            if (i, j) in visited:
                continue

            area = 0
            perimeter = 0
            q = collections.deque([(i, j)])
            visited.add((i, j))
            while q:
                k, l = q.popleft()
                area += 1
                for dk, dl in DIR4:
                    nk = k + dk
                    nl = l + dl
                    if nk < 0 or nk >= n or nl < 0 or nl >= m or ls[nk][nl] != ls[k][l]:
                        perimeter += 1
                    elif (nk, nl) not in visited:
                        q.append((nk, nl))
                        visited.add((nk, nl))

            ans += area * perimeter

    return ans


def part2():
    ans = 0

    visited = set()
    for i in range(n):
        for j in range(m):
            if (i, j) in visited:
                continue

            area = 0
            sides = 0
            outside_points = set()
            q = collections.deque([(i, j)])
            visited.add((i, j))
            while q:
                k, l = q.popleft()
                area += 1
                for dk, dl in DIR4:
                    nk = k + dk
                    nl = l + dl
                    if nk < 0 or nk >= n or nl < 0 or nl >= m or ls[nk][nl] != ls[k][l]:
                        count = sum(map(lambda d: (nk + d[0], nl + d[1], dk, dl) in outside_points, DIR4))
                        if count == 0:
                            sides += 1
                        elif count > 1:
                            sides -= 1
                        outside_points.add((nk, nl, dk, dl))
                    elif (nk, nl) not in visited:
                        q.append((nk, nl))
                        visited.add((nk, nl))

            ans += area * sides

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
