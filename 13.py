from aoc import *

DAY = 13

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import re

ls = [[]]
for line in s.splitlines():
    if line == "":
        ls.append([])
    else:
        ls[-1].append(line)


def parse_button(line):
    pattern = r"^Button [A-Z]: X\+(\d+), Y\+(\d+)$"
    match = re.search(pattern, line)
    return int(match.group(1)), int(match.group(2))


def parse_prize(line):
    pattern = r"^Prize: X=(\d+), Y=(\d+)$"
    match = re.search(pattern, line)
    return int(match.group(1)), int(match.group(2))


def nb_tokens(button_a, button_b, prize):
    det = button_a[0] * button_b[1] - button_a[1] * button_b[0]
    if det == 0:
        return 0

    x = button_b[1] * prize[0] - button_b[0] * prize[1]
    if x % det != 0:
        return 0
    a = x // det

    y = - button_a[1] * prize[0] + button_a[0] * prize[1]
    if y % det != 0:
        return 0
    b = y // det

    if a < 0 or b < 0:
        return 0

    return 3 * a + b


def part1():
    ans = 0

    for l in ls:
        button_a = parse_button(l[0])
        button_b = parse_button(l[1])
        prize = parse_prize(l[2])

        ans += nb_tokens(button_a, button_b, prize)

    return ans


def part2():
    ans = 0

    for l in ls:
        button_a = parse_button(l[0])
        button_b = parse_button(l[1])
        prize = tuple(map(lambda x: x + 10000000000000, parse_prize(l[2])))

        ans += nb_tokens(button_a, button_b, prize)

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
