from aoc import *

DAY = 21

s = get_input(DAY)
# s = get_example(DAY, 3)

import collections
import math
import functools

ls = s.splitlines()

num_positions = {
    "7": (0, 0),
    "8": (0, 1),
    "9": (0, 2),
    "4": (1, 0),
    "5": (1, 1),
    "6": (1, 2),
    "1": (2, 0),
    "2": (2, 1),
    "3": (2, 2),
    "0": (3, 1),
    "A": (3, 2),
}


def get_numpad_paths(start, end):
    result = []

    start_pos = num_positions[start]
    end_pos = num_positions[end]

    i_diff = end_pos[0] - start_pos[0]
    j_diff = end_pos[1] - start_pos[1]
    v = "v" if i_diff > 0 else "^"
    h = ">" if j_diff > 0 else "<"

    if start_pos[1] != 0 or end_pos[0] != 3:
        result.append(v * abs(i_diff) + h * abs(j_diff) + "A")

    if start_pos[0] != 3 or end_pos[1] != 0:
        result.append(h * abs(j_diff) + v * abs(i_diff) + "A")

    return result


dir_path = {
    "A": {
        "A": [""],
        "^": ["<"],
        ">": ["v"],
        "v": ["v<", "<v"],
        "<": ["v<<"],
    },
    "^": {
        "^": [""],
        "A": [">"],
        ">": [">v", "v>"],
        "v": ["v"],
        "<": ["v<"],
    },
    ">": {
        ">": [""],
        "A": ["^"],
        "^": ["^<", "<^"],
        "v": ["<"],
        "<": ["<<"],
    },
    "v": {
        "v": [""],
        "A": ["^>", ">^"],
        "^": ["^"],
        ">": [">"],
        "<": ["<"],
    },
    "<": {
        "<": [""],
        "A": [">>^"],
        "^": [">^"],
        "v": [">"],
        ">": [">>"],
    }
}


@functools.cache
def rec(p, d, depth):
    if depth == 1:
        return 1 + len(dir_path[p][d][0])

    result = float("inf")
    for path in dir_path[p][d]:
        length = 0
        current_dir = "A"
        for direction in path + "A":
            length += rec(current_dir, direction, depth - 1)
            current_dir = direction
        if length < result:
            result = length

    return result


def solve(n):
    ans = 0

    for l in ls:
        all_directions = {""}
        current_num = "A"
        for num in l:
            new_directions = set()
            for directions in all_directions:
                new_directions.update(map(lambda x: directions + x, get_numpad_paths(current_num, num)))
            current_num = num
            all_directions = new_directions

        length = float("inf")
        for directions in all_directions:
            ld = 0
            current_dir = "A"
            for d in directions:
                ld += rec(current_dir, d, n)
                current_dir = d
            if ld < length:
                length = ld

        ans += int(l[:-1]) * length

    return ans


def part1():
    return solve(2)

def part2():
    return solve(25)


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
