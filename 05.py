from aoc import *

DAY = 5

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
from functools import cmp_to_key

ls = s.splitlines()


def part1():
    ans = 0

    g = collections.defaultdict(set)

    i = 0
    while ls[i] != "":
        a, b = map(int, ls[i].split("|"))
        g[a].add(b)
        i += 1

    i += 1

    while i < len(ls):
        update = list(map(int, ls[i].split(",")))

        seen = set()
        for page in update:
            if seen & g[page]:
                break
            seen.add(page)
        else:
            ans += update[len(update) // 2]

        i += 1

    return ans


def part2():
    ans = 0

    g = collections.defaultdict(set)

    i = 0
    while ls[i] != "":
        a, b = map(int, ls[i].split("|"))
        g[a].add(b)
        i += 1

    i += 1

    incorrectly_ordered_updates = []
    while i < len(ls):
        update = list(map(int, ls[i].split(",")))

        seen = set()
        for page in update:
            if seen & g[page]:
                incorrectly_ordered_updates.append(update)
                break
            seen.add(page)

        i += 1

    for update in incorrectly_ordered_updates:
        update.sort(key=cmp_to_key(lambda a, b: (a in g[b]) - (b in g[a])))
        ans += update[len(update) // 2]

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
