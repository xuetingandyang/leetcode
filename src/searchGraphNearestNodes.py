# Given a undirected graph, a node and a target,
# return the nearest node to given node which value of it is target,
# return NULL if you can't find.
#
# There is a mapping store the nodes' values in the given parameters.
#
# Notice: It's guaranteed there is only one available solution

# 2------3   5
#  \     |   |
#    \   |   |
#     \  |   |
#       1 ---4
# Give a node 1, target is 50
#
# there a hash named values which is [3,4,10,50,50], represent:
# Value of node 1 is 3
# Value of node 2 is 4
# Value of node 3 is 10
# Value of node 4 is 50
# Value of node 5 is 50
#
# Return node 4

## definition for graph node
# class UndirectedGraphNode():
#     self.label: int
#     self.neighbors: List[UndirectedGraphNode]

class Solution:
    def searchNode(self,
                   graph: List[UndirectedGraphNode],
                   values: dict(),
                   node: UndirectedGraphNode,
                   target: int) -> UndirectedGraphNode:
        if not node:
            return node

        queue = collections.deque()
        hashset = set()

        queue.append(node)
        hashset.add(node)   # store nodes that already visited

        while queue:
            cur_node = queue.popleft()
            if values[cur_node] == target:  # find it
                return cur_node

            for neighbor in cur_node.neighbors:
                if neighbor not in hashset:
                    # add it into hashset
                    hashset.add(neighbor)
                    queue.append(neighbor)

        # finish BFS traversal, but still not found --> return NONE
        return None
