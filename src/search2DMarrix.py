# Write an efficient algorithm that searches for a value in an m x n matrix.
#  O(log(n) + log(m)) time
# This matrix has the following properties:
# Integers in each row are sorted from left to right.
# The first integer of each row is greater than the last integer of the previous row.

# Example 1:
# 	Input:  [[5]],2
# 	Output: false
#
# 	Explanation:
# 	false if not included.
#
# Example 2:
# 	Input:  [
#     [1, 3, 5, 7],
#     [10, 11, 16, 20],
#     [23, 30, 34, 50]
# ],3
# 	Output: true
#
# 	Explanation:
# 	return true if included.

# O(log m + log n) = O(log mn)
# binary search in [0, m*n]


class Solution:
    """
    @param matrix: matrix, a list of lists of integers
    @param target: An integer
    @return: a boolean, indicate whether matrix contains target
    """

    def computeRowCol(self, idx, n):
        if idx % n == 0:
            row, col = idx // n - 1, n - 1
        else:
            row = idx // n
            col = idx % n - 1
        return (row, col)

    def searchMatrix(self, matrix, target):
        m, n = len(matrix), len(matrix[0])
        start, end = 1, m * n

        while start + 1 < end:
            mid = start + (end - start) // 2
            row, col = self.computeRowCol(mid, n)
            if matrix[row][col] == target:
                return True
            if matrix[row][col] > target:
                end = mid
            if matrix[row][col] < target:
                start = mid

        # print(start, end)
        row1, col1 = self.computeRowCol(start, n)
        row2, col2 = self.computeRowCol(end, n)

        if matrix[row1][col1] == target or matrix[row2][col2] == target:
            return True

        return False

if __name__ == "__main__":
    test = Solution()
    a = [[2,3,4], [5,6,7]]
    print(test.searchMatrix(a, 1))
    print(test.searchMatrix(a, 2))
    print(test.searchMatrix(a, 7))