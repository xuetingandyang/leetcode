# Given a big sorted array with positive integers sorted by ascending order.
# The array is so big that you can not get the length of the whole array directly.
# you can only access the k-th number by "ArrayReader.get(k)"
# find the first index of a target number in O(long k) where k is the first index of the target number

from typing import List

class Solution:
    def searchBigArray(self, nums:List[int], target:int) -> int:
        # 1. get the index >= target in O(log k)
        # 2. binary search the target btw 0 and index
        index = 1
        while (nums[index - 1] < target):
            index *= 2  # double idea to find possible index in O(logk)

        start = index // 2
        end = index + 1

        while start + 1 < end:
            mid = start + (end - start) // 2
            if nums[mid] < target:
                start = mid
            else:
                end = mid

        if nums[start] == target:
             return start
        if nums[end] == target:
             return end
        else:
            return -1

if __name__ == "__main__":
    test = Solution()
    nums = [1]*10 + [2]*20 + [3]*30 + [4]*40 + [5]*50 + [6]*600  # 100
    print(test.searchBigArray(nums, 6))
