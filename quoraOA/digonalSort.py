from typing import List

class Solution:
    def helper(self, matrix:List[List[int]], start:List[int], dir: List[int]):
        """
        sort matrix to make each diagonal is incremental
        """
        num_row = len(matrix)
        num_col = len(matrix[0])
        i, j = start

        while i < num_row and j < num_col:
            count = 0
            temp = []
            while count + i < num_row and count + j < num_col:
                temp.append(matrix[count + i][count + j])
                count += 1
            temp.sort()

            # rearrange the current diagonal
            count = 0
            while count + i < num_row and count + j < num_col:
                matrix[count + i][count + j] = temp[count]
                count += 1

            # next diagonal
            i += dir[0]
            j += dir[1]

    def diagonalSort(self, matrix:List[List[int]]) -> List[List[int]]:

        self.helper(matrix, [0,0], [0,1])
        self.helper(matrix, [0,0], [1,0])

        return matrix


test = Solution()
sorted = test.diagonalSort(
    [
        [1,2,3,4,5],
        [3,7,6,4,9],
        [8,2,7,3,6],
        [9,2,7,3,4]
    ]
)
print(sorted)
