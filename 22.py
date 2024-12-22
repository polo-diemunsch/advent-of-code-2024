from aoc import *

DAY = 22

s = get_input(DAY)
# s = get_example(DAY, 1)

import collections
import math

ls = s.splitlines()


def get_next_secret_number(secret_number):
    secret_number = ((secret_number * 64) ^ secret_number) % 16777216
    secret_number = ((secret_number // 32) ^ secret_number) % 16777216
    secret_number = ((secret_number * 2048) ^ secret_number) % 16777216
    return secret_number


def part1():
    ans = 0

    for l in ls:
        secret_number = int(l)
        for _ in range(2000):
            secret_number = get_next_secret_number(secret_number)
        ans += secret_number

    return ans


def part2():
    counter = collections.Counter()

    for l in ls:
        secret_number = int(l)
        last_digit = secret_number % 10
        deltas = []
        for _ in range(4):
            secret_number = get_next_secret_number(secret_number)
            digit = secret_number % 10
            deltas.append(last_digit - digit)
            last_digit = digit

        deltas = tuple(deltas)
        counter[deltas] += last_digit
        seen = {deltas}
        for _ in range(2000 - 4):
            secret_number = get_next_secret_number(secret_number)
            digit = secret_number % 10
            deltas = (*deltas[1:], last_digit - digit)
            last_digit = digit
            if deltas not in seen:
                counter[deltas] += last_digit
                seen.add(deltas)

    return max(counter.values())


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
