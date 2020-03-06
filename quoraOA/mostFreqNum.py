__author__ = ' Zhen Wang'

from typing import List

class Solution:

    def findMostFreqNum(self, nums: List[int]) -> List[int]:
        """
        nums = [22, 3, 33, 2, 5]
        find most common digit in nums
        '2' and '3': appear 3 times
        output: [2,3]
        """

        if nums is None or len(nums) == 0:
            return []

        freq = float('-inf')

        map = {}

        for num in nums:
            tmp = num
            while tmp != 0:
                map[tmp % 10] = map[tmp % 10] + 1 if tmp % 10 in map else 1
                freq = map[tmp % 10] if map[tmp % 10] > freq else freq
                tmp //= 10

        rst = []
        for key in map:
            if map[key] == freq:
                rst.append(key)
        return rst


sol = Solution()
nums = [22, 3, 33, 2, 5]
print(sol.findMostFreqNum(nums))