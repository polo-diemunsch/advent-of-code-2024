from aoc import *

DAY = 2

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()


def part1():
    ans = 0

    for l in ls:
        a = ints(l)
        good = a == sorted(a) or a == sorted(a, reverse=True)
        i = 1
        while good and i < len(a):
            good = 1 <= abs(a[i] - a[i-1]) <= 3
            i += 1

        if good:
            ans += 1

    return ans


def part2():
    ans = 0

    def is_good(is_increasing, diff):
        return (is_increasing and 1 <= diff <= 3) or (not is_increasing and -3 <= diff <= -1)

    for l in ls:
        a = ints(l)
        if len(a) <= 2:
            ans += 1
            continue

        q = []

        diff = a[1] - a[0]
        if 1 <= abs(diff) <= 3:
            q.append((diff > 0, 2, False))

        diff = a[2] - a[1]
        if 1 <= abs(diff) <= 3:
            q.append((diff > 0, 3, True))

        diff = a[2] - a[0]
        if 1 <= abs(diff) <= 3:
            q.append((diff > 0, 3, True))

        while q:
            is_increasing, i, skipped = q.pop()

            if i >= len(a) or (i == len(a) - 1 and not skipped):
                ans += 1
                break

            diff = a[i] - a[i-1]
            if is_good(is_increasing, diff):
                q.append((is_increasing, i+1, skipped))
            elif not skipped:
                diff = a[i+1] - a[i-1]
                if is_good(is_increasing, diff):
                    q.append((is_increasing, i+2, True))

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
