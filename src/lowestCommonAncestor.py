
# Given the root and two nodes in a Binary Tree.
# Find the lowest common ancestor(LCA) of the two nodes.
# The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
# The node has an extra attribute parent which point to the father of itself.

# (1) The root's parent is null.
# (2) access all the nodes from the desired node ‘m’ till root node and mark them visited.
# (3) access all the nodes from the desired node ‘n’ till first visited node comes.
# (4) This node is the lowest common ancestor

# Example
# For the following binary tree:
#   4
#  / \
# 3   7
#    / \
#   5   6
# LCA(3, 5) =4
# LCA(5, 6) =7
# LCA(6, 7) =7

from typing import List

# class ParentTreeNode:
#     def __init__(self, x, parent, left, right):


class Solution:
    def findLCA(self, a: ParentTreeNode, b: ParentTreeNode, root: ParentTreeNode) -> ParentTreeNode:
        """
        :param a,b: Two node in the tree
        :param root: root of the tree
        :return: The lowest common ancestor of A and B
        """

        if (not root) or (not a) or (not b):
            return None

        path_a = self.findPathToRoot(a)
        path_b = self.findPathToRoot(b)

        idx_a, idx_b = len(path_a) - 1, len(path_b) - 1

        while (idx_a >= 0 and idx_b >= 0):
            if (path_a[idx_a] != path_b[idx_b]):
                # when find the first not-common ancestor
                # --> break out of the while loop
                break
            LCA = path_a[idx_a]
            idx_a -= 1
            idx_b -= 1

        return LCA

    def findPathToRoot(self, node: ParentTreeNode) -> List[ParentTreeNode]:

        path = []

        if not node:
            return path

        while node:
            path.append(node)
            node = node.parent

        return path





