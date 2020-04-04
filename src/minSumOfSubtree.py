# Question
# Description Given a binary tree, find the subtree with minimum sum.
# Return the root of the subtree.
#
# Notice LintCode will print the subtree which root is your return node.
# It's guaranteed that there is only one subtree with minimum sum
# and the given binary tree is not an empty tree.
#
# Example Given a binary tree:
#
#       1
#     /   \
#  -5      2
#  / \     /  \
# 0   2   -4  -5

# return the node 1

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def minSubtree(self, root):
        """
        recursion-divide conquer (with return) + traversal (global var)
        :param root: TreeNode
        :return: TreeNode
        """
        self.min_sum = float('inf')
        self.subtree = TreeNode(None)

        self.helper(root)

        return self.subtree

    def helper(self, root):
        """return sum"""
        if root is None:
            return 0
        # divide conquer + merge
        sum = self.helper(root.left) + self.helper(root.right) + root.val

        # compare
        if sum < self.min_sum:
            self.min_sum = sum
            self.subtree = root

        return sum


if __name__ == "__main__":
    # root = [TreeNode(item) for item in [1,None,2,3,4,None,5,6,None,7]]
    root = TreeNode(1)
    root.left = TreeNode(-5)
    root.right = TreeNode(2)
    root.left.left = TreeNode(0)
    root.left.right = TreeNode(2)
    root.right.left = TreeNode(-4)
    root.right.right = TreeNode(-5)

    test = Solution()
    res = test.minSubtree(root)
    print(test)




