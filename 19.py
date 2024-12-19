from aoc import *

DAY = 19

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()
patterns = {}
for pattern in ls[0].split(", "):
    current = patterns
    for letter in pattern:
        if letter not in current:
            current[letter] = {}
        current = current[letter]
    current["."] = {}


def arrangements_count(design):
    n = len(design)
    dp = [0] * (n + 1)
    dp[0] = 1
    for i in range(n):
        if dp[i] == 0:
            continue
        current = patterns.get(design[i], None)
        j = 1
        while current is not None and i + j <= n:
            if "." in current:
                dp[i + j] += dp[i]
            current = current.get(design[i + j], None) if i + j < n else None
            j += 1

    return dp[-1]


def part1():
    return sum(arrangements_count(design) > 0 for design in ls[2:])


def part2():
    return sum(arrangements_count(design) for design in ls[2:])


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
