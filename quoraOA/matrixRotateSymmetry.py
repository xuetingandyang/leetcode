from typing import List

class Solution:
    def matrixRotate90(self, matrix:List[List[int]]):
        """
        rotate a matrix clockwise 90 degrees.
        :param matrix:
        :return:
        """

        n = len(matrix[0])
        for i in range(n // 2 + n % 2):
            for j in range(n // 2):
                tmp = matrix[n - 1 - j][i]
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1]
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i]
                matrix[j][n - 1 - i] = matrix[i][j]
                matrix[i][j] = tmp

        return

    def matrixReverseMainDiagonal(self, matrix: List[List[int]]):
        n = len(matrix)
        for i in range(n):
            for j in range(i, n):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

        return

    def matrixReverseSecondDiagonal(self, matrix: List[List[int]]):
        n = len(matrix)
        for i in range(n):
            for j in range(n - i):
                matrix[i][j], matrix[n - j - 1][n - i - 1] = matrix[n - j - 1][n - i - 1], matrix[i][j]

        return


sol = Solution()
matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]

sol.matrixRotate90(matrix)

sol.matrixReverseMainDiagonal(matrix)

sol.matrixReverseSecondDiagonal(matrix)
