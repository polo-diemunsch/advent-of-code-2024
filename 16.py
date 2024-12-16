from aoc import *

DAY = 16

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import heapq

ls = s.splitlines()
initial_pos = find_pos(ls, "S")

def part1():
    pq = [(0, *initial_pos, 2)]
    visited = {(*initial_pos, 2)}
    while pq:
        c, i, j, d = heapq.heappop(pq)

        if ls[i][j] == "E":
            return c

        for k in range(len(DIR4)):
            ni = i + DIR4[k][0]
            nj = j + DIR4[k][1]

            if ls[ni][nj] == "#":
                continue

            if k == d and (ni, nj, d) not in visited:
                heapq.heappush(pq, (c + 1, ni, nj, d))
                visited.add((ni, nj, d))
            elif (i, j, k) not in visited:
                heapq.heappush(pq, (c + 1000, i, j, k))
                visited.add((i, j, k))

    return -1


def part2():
    pq = [(0, *initial_pos, 2)]
    dist = {(*initial_pos, 2): 0}
    parents = {(*initial_pos, 2): []}
    end_pos = None
    while pq:
        c, i, j, d = heapq.heappop(pq)

        if ls[i][j] == "E":
            end_pos = (i, j, d)
            break

        for k in range(len(DIR4)):
            ni = i + DIR4[k][0]
            nj = j + DIR4[k][1]

            if ls[ni][nj] == "#":
                continue

            nc = c + (1 if k == d else 1000)
            if k == d:
                if (ni, nj, d) not in dist:
                    heapq.heappush(pq, (nc, ni, nj, d))
                    dist[(ni, nj, d)] = nc
                    parents[(ni, nj, k)] = [(i, j, d)]
                elif dist[(ni, nj, k)] == nc:
                    parents[(ni, nj, k)].append((i, j, d))
            elif (i, j, k) not in dist:
                heapq.heappush(pq, (nc, i, j, k))
                dist[(i, j, k)] = nc
                parents[(i, j, k)] = [(i, j, d)]
            elif dist[(i, j, k)] == nc:
                parents[(i, j, k)].append((i, j, d))

    if end_pos is None:
        return -1

    q = collections.deque([end_pos])
    best_paths_tiles = {end_pos}
    while q:
        current = q.popleft()

        for parent in parents[current]:
            if parent not in best_paths_tiles:
                q.append(parent)
                best_paths_tiles.add(parent)

    return len({tile[:2] for tile in best_paths_tiles})


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
