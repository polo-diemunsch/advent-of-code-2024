from aoc import *

DAY = 17

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math

ls = s.splitlines()

instructions = list(map(int, ls[4].split(": ")[1].split(",")))


def run_program(a):
    b = 0
    c = 0

    i = 0
    while i < len(instructions):
        opcode = instructions[i]
        operand = instructions[i + 1]

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
    a = int(ls[0].split(": ")[1])
    return ",".join(map(str, run_program(a)))


def part2(a=0, i=len(instructions)-1):
    if i < 0:
        return a

    current_a = a * 8
    for j in range(8):
        program_output = run_program(current_a + j)
        if all(instructions[i] == next(program_output) for i in range(i, len(instructions))):
            ans = part2(current_a + j, i - 1)
            if ans > 0:
                return ans

    return -1

# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
