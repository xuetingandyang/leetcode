from typing import List

class Solution:
    def helper(self, matrix:List[List[bool]], m:int, n:int):
        # return the min product of idx in matrix
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == True:
                    return (i + 1) * (j + 1)


    def minProductIdx(self, m:int, n:int, L:List[List[int]]) -> int:
        """
        given m*n matrix, each element = row_idx * col_idx (start with 1)
        for List L:
        eg: L=[[0], [1,i], [0], [2,j], [0]]
        return min product of idx
        block row i
        return min product of idx
        block col j
        return min product of idx
        """
        matrix = [[True for i in range(n)] for j in range(m)]
        result = []
        for item in L:
            if item[0] == 0:
                result.append(self.helper(matrix, m, n))

            if item[0] == 1:    # block row
                # matrix[item[1] - 1] = [False] * n
                for j in range(n):
                    matrix[item[1] - 1][j] = False
            if item[0] == 2:    # block col
                for i in range(m):
                    matrix[i][item[1] - 1] = False
        return result

test = Solution()
print(test.minProductIdx(5,4,[[0], [1,3], [0], [2,1], [0], [2,2], [0], [1,1], [0]]))
# 1,1,2,3,6