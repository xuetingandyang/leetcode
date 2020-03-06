class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if len(matrix) == 0 or len(matrix[0]) == 0:
            return False

        m, n = len(matrix), len(matrix[0])
        row, col = m - 1, 0
        count = 0

        while row >= 0 and col < n:

            ### 3 if (wrong), because under each if, row, col will change. It will affect next 'if'

            if matrix[row][col] > target:
                row -= 1
            elif matrix[row][col] < target:
                col += 1
            # if matrix[row][col] == target:
            else:
                return True

            print(row, col)

        return False



    def searchMatrixCount(self, matrix, target):
        """
        return the occurrence number of 'target' in 'matrix'
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if len(matrix) == 0 or len(matrix[0]) == 0:
            return 0

        m, n = len(matrix), len(matrix[0])
        row, col = m - 1, 0
        count = 0

        while row >= 0 and col < n:

            if matrix[row][col] > target:
                row -= 1
            elif matrix[row][col] < target:
                col += 1
            # if matrix[row][col] == target:
            else:
                # return True
                count += 1
                row -= 1
                col += 1
            print(row, col)

        return count



if __name__ == "__main__":
    test = Solution()
    a = [[1, 4, 7, 11, 15],
         [2, 5, 8, 12, 19],
         [3, 6, 9, 16, 22],
         [10, 13, 14, 17, 24],
         [18, 21, 23, 26, 30]]
    k = 20
    print(test.searchMatrix(a, k))
    a = []
    k = 20
    print(test.searchMatrix(a, k))
