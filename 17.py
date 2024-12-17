from aoc import *

DAY = 17

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()

a = int(ls[0].split(": ")[1])
ops = list(map(int, ls[4].split(": ")[1].split(",")))


def run_program(a, ops):
    b = 0
    c = 0

    i = 0
    while i < len(ops):
        opcode = ops[i]
        operand = ops[i + 1]

        combo_operand = operand
        if combo_operand == 4:
            combo_operand = a
        elif combo_operand == 5:
            combo_operand = b
        elif combo_operand == 6:
            combo_operand = c
        elif combo_operand == 7:
            assert False

        if opcode == 0:
            a >>= combo_operand
        elif opcode == 1:
            b ^= operand
        elif opcode == 2:
            b = combo_operand % 8
        elif opcode == 3:
            if a != 0:
                i = operand
                continue
        elif opcode == 4:
            b ^= c
        elif opcode == 5:
            yield combo_operand % 8
        elif opcode == 6:
            b = a >> combo_operand
        elif opcode == 7:
            c = a >> combo_operand
        else:
            assert False

        i += 2


def part1():
    return ",".join(map(str, run_program(a, ops)))


def part2():
    ans = 0

    i = len(ops) - 1
    while i >= 0:
        ans *= 8
        while next(run_program(ans, ops)) != ops[i]:
            ans += 1
        i -= 1

    return ans

# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
