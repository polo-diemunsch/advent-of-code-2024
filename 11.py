from aoc import *

DAY = 11

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()

def blink(n):
    stones = collections.Counter(ints(ls[0]))
    for _ in range(n):
        new_stones = collections.defaultdict(int)
        for stone, nb in stones.items():
            string_stone = str(stone)
            if stone == 0:
                new_stones[1] += nb
            elif len(string_stone) % 2 == 0:
                new_stones[int(string_stone[:len(string_stone) // 2])] += nb
                new_stones[int(string_stone[len(string_stone) // 2:])] += nb
            else:
                new_stones[stone * 2024] += nb
        stones = new_stones

    return sum(stones.values())


def part1():
    return blink(25)


def part2():
    return blink(75)


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
