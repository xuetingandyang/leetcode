__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def countFreq(self, array: List[int], matrix: List[List[int]]) -> int:
        """
        array = [1,1,2,3,2]
        matrix = [[1,2,1],
                  [2,4,2],
                  [0,3,1]]
        output : 5

        因为在matrix[0], array的index 1到2区间出现了1 一次，
        matrix[1], array的index 2到4区间出现2 两次。
        matrx[2], array的index 0到3区间出现1 两次

        sol: build a hash table "map":
        key = array_values, values = idx
        "1":[0,1]
        "2":[2,4]
        "3":[3]
        For each row in matrix, just check if map["target"] has overlap in [l,r]
        """
        map = {}
        for i, item in enumerate(array):
            if item not in map:
                map[item] = []
            map[item].append(i)

        count = 0
        for i in range(len(matrix)):
            l, r, target = matrix[i][0], matrix[i][1], matrix[i][2]
            if target in map:
                for idx in map[target]:
                    if idx >= l and idx <= r:   # this idx is in the range(l, r)
                        count += 1
        return count


if __name__ == "__main__":
    sol = Solution()
    arr = [1, 1, 2, 3, 2]
    matrix = [
        [1, 2, 1],
        [2, 4, 2],
        [0, 4, 2]
    ]
    print(sol.countFreq(arr, matrix))

