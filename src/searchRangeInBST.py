# Given a binary search tree and a range [k1, k2],
# return node values within a given range in ascending order.

# Input：{5},6,10
# Output：[]
# No number between 6 and 10

# Input：{20,8,22,4,12},10,22
# Output：[12,20,22]
# Explanation：
#         20
#        /  \
#       8   22
#      / \
#     4   12
# it will be serialized {20,8,22,4,12}
# [12,20,22] between 10 and 22

"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""
from typing import List


class Solution:
    """
    @param root: param root: The root of the binary search tree
    @param k1: An integer
    @param k2: An integer
    @return: return: Return all keys that k1<=key<=k2 in ascending order
    """

    def searchRange(self, root: TreeNode, k1: int, k2: int) -> List[int]:

        res = []

        if not root:
            return res

        if root.val < k1:  # search in right subtree
            return self.searchRange(root.right, k1, k2)
        elif root.val > k2:  # search in left subtree
            return self.searchRange(root.left, k1, k2)
        else:
            # k1 <= root <= k2
            # results = left + root + right

            left = self.searchRange(root.left, k1, k2)
            right = self.searchRange(root.right, k1, k2)

            res.extend(left)
            res.append(root.val)
            res.extend(right)

        return res
