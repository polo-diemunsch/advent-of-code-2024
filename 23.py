from aoc import *

DAY = 23

s = get_input(DAY)
# s = get_example(DAY)

import collections
import math
import networkx as nx

ls = s.splitlines()
g = nx.Graph()
for l in ls:
    a, b = l.split("-")
    g.add_edge(a, b)


def part1():
    return sum(len(clique) == 3 and any(node[0] == "t" for node in clique) for clique in nx.enumerate_all_cliques(g))


def part2():
    return ",".join(sorted(max(nx.find_cliques(g), key=len)))


# submit(DAY, part1(), 1)
# submit(DAY, part2(), 2)
