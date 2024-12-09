from aoc import *

DAY = 9

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import sys
sys.set_int_max_str_digits(20000)

ls = s.splitlines()


def part1():
    ans = 0

    disk = []
    is_file = True
    file_id = 0
    for digit in map(int, list(ls[0])):
        if is_file:
            disk.extend([file_id] * digit)
            file_id += 1
        else:
            disk.extend([-1] * digit)
        is_file = not is_file

    i = 0
    j = len(disk) - 1
    while disk[j] == -1:
        j -= 1
    while disk[i] != -1:
        i += 1
    while j > i:
        disk[i], disk[j] = disk[j], disk[i]
        while disk[j] == -1:
            j -= 1
        while disk[i] != -1:
            i += 1

    for i, file_id in enumerate(disk):
        if file_id >= 0:
            ans += i * file_id

    return ans


def part2():
    ans = 0

    disk = []
    is_file = True
    file_id = 0
    for digit in map(int, list(ls[0])):
        if is_file:
            disk.append((file_id, digit))
            file_id += 1
        else:
            disk.append((-1, digit))
        is_file = not is_file

    j = len(disk) - 1
    done = set()
    while j > 0:
        while disk[j][0] == -1 and disk[j][0] in done:
            j -= 1
        done.add(disk[j][0])
        i = 0
        while i < j and (disk[i][0] != -1 or disk[i][1] < disk[j][1]):
            i += 1
        if i >= j:
            j -= 1
            continue

        if disk[i][1] == disk[j][1]:
            disk[i], disk[j] = disk[j], disk[i]
        else:
            disk[i] = (-1, disk[i][1] - disk[j][1])
            disk.insert(i, disk[j])
            disk[j + 1] = (-1, disk[j + 1][1])

    i = 0
    for file_id, size in disk:
        if file_id >= 0:
            for j in range(size):
                ans += (i + j) * file_id
        i += size

    return ans


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
