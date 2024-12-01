from aoc import *

DAY = 1

s = get_input(DAY)
#s = get_example(DAY)

import collections
import math

ls = s.splitlines()


def part1():
    a = []
    b = []
    for l in ls:
        c, d = ints(l)
        a.append(c)
        b.append(d)

    a.sort()
    b.sort()

    return sum(abs(c - d) for c, d in zip(a, b))


def part2():
    a = []
    b = []
    for l in ls:
        c, d = ints(l)
        a.append(c)
        b.append(d)

    bb = collections.Counter(b)

    return sum(c * bb[c] for c in a)


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
