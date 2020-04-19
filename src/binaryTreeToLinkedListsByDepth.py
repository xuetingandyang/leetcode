# Lintcode: 242. Convert Binary Tree to Linked Lists by Depth

# Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).

# Input: {1,2,3,4}
# Output: [1->null,2->3->null,4->null]
# Explanation:
#         1
#        / \
#       2   3
#      /
#     4
#
# Input: {1,#,2,3}
# Output: [1->null,2->null,3->null]
# Explanation:
#     1
#      \
#       2
#      /
#     3


"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
"""
from typing import List
from collections import deque


class Solution:
    # @param {TreeNode} root the root of binary tree
    # @return {ListNode[]} a lists of linked list
    def binaryTreeToLists(self, root: TreeNode) -> List[ListNode]:
        # Write your code here
        # BFS: time: O(n),
        # space: queue, temp save all nodes in current level -> O(n)
        queue, res = deque(), []
        if not root:
            return res

        queue.append(root)

        while queue:
            first = ListNode(-1)    # virtual head of current level
            temp = first    # 'temp' is the first ListNode for current level

            for i in range(len(queue)):
                node = queue.popleft()
                cur_listnode = ListNode(node.val)
                first.next = cur_listnode
                first = cur_listnode

                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            res.append(temp.next)
        return res

##### method 2: DFS: recursive preorder
class Solution:
    def binaryTreeToLists2(self, root: TreeNode) -> List[ListNode]:
        res = []

        self.helper(root, 1, res)

        return res

    def helper(self, node, depth, res):
        if node:
            cur_node = ListNode(node.val)
            if len(res) < depth:  # did not reach current depth
                res.append(cur_node)
            else:  # reach the current depth, start to link nodes
                cur_node.next = res[depth - 1]
                res[depth - 1] = cur_node

            # DFS preoreder, so 'right' then 'left'
            self.helper(node.right, depth + 1, res)
            self.helper(node.left, depth + 1, res)


