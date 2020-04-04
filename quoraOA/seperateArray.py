__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def seperateArray(self, array: List[int]) -> List[List[int]]:
        """
        separate array into 2 sub-arrays.
        with each sub-array has unique values
        """

        if array is None or len(array) % 2 != 0:
            return []

        rst = [[],[]]

        array = sorted(array)

        for i in range(len(array)):
            rst[i % 2].append(array[i])
            if i // 2 - 1 >= 0 and rst[i % 2][i // 2] == rst[i % 2][i //2 - 1]:
                return []
        return rst


sol = Solution()
arr  =[1, 1, 1, 2, 4, 5, 6, 7]
print(sol.seperateArray(arr))