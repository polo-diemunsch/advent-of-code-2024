from aoc import *

DAY = 3

s = get_input(DAY)
#s = get_example(DAY)

import collections
import math
import re

ls = s.splitlines()


def part1():
    ans = 0

    pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)"

    for l in ls:
        for a, b in re.findall(pattern, l):
            ans += int(a) * int(b)

    return ans


def part2():
    ans = 0

    pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))|(don't\\(\\))"

    mult = True
    for l in ls:
        for a, b, do, dont in re.findall(pattern, l):
            if do:
                mult = True
            elif dont:
                mult = False
            elif mult:
                ans += int(a) * int(b)

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
