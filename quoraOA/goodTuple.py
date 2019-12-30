__author__ = ' Zhen Wang'
from typing import List


class Solution:

    def findGoodTuple(self, nums: List[int]):
        """
        nums = [4, 6, 4, 4, 4, 4, 4]
        find tuple (len=3) with only two unique items
        """
        rst = 0
        for i in range(1, len(nums) - 1):
            if (nums[i] == nums[i - 1] and nums[i] != nums[i + 1])\
                    or (nums[i] == nums[ i + 1] and nums[i] != nums[i - 1])\
                    or (nums[i - 1] == nums[i + 1] and nums[i] != nums[i + 1]):
                rst += 1
        return rst


sol = Solution()
nums = [4, 6, 4, 4, 4, 4, 4]
print(sol.findGoodTuple(nums))