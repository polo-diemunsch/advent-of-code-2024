from aoc import *

DAY = 7

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import operator

ls = s.splitlines()


def part1():
    ans = 0

    for l in ls:
        i = l.index(":")
        target = int(l[:i])
        equation = ints(l[i+2:])

        current = {equation[0]}
        i = 1
        while i < len(equation) and len(current) > 0:
            new_current = set()
            for c in current:
                for op in (operator.add, operator.mul):
                    nc = op(c, equation[i])
                    if nc <= target:
                        new_current.add(nc)

            current = new_current
            i += 1

        if target in current:
            ans += target

    return ans


def part2():
    ans = 0

    for l in ls:
        i = l.index(":")
        target = int(l[:i])
        equation = ints(l[i+2:])

        current = {equation[0]}
        i = 1
        while i < len(equation) and len(current) > 0:
            new_current = set()
            for c in current:
                for op in (operator.add, operator.mul, lambda x, y: int(f"{x}{y}")):
                    nc = op(c, equation[i])
                    if nc <= target:
                        new_current.add(nc)

            current = new_current
            i += 1

        if target in current:
            ans += target

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
