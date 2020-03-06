# Given an array of integers nums sorted in ascending order,
# find the starting and ending position of a given target value.
# Your algorithm's runtime complexity must be in the order of O(log n).
#
# If the target is not found in the array, return [-1, -1].
# Example 1:
# Input: nums = [5,7,7,8,8,10], target = 8
# Output: [3,4]
# Example 2:
# Input: nums = [5,7,7,8,8,10], target = 6
# Output: [-1,-1]

from typing import List

class Solution:
    def findLeftRightIdx(self, nums: List[int], target: int, left: bool):
        start, end = 0, len(nums) - 1
        while start + 1 < end:
            mid = start + (end - start) // 2
            if nums[mid] > target or (left and nums[mid] == target):
                end = mid
            else:
                start = mid

        return start, end

    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if len(nums) == 0:
            return [-1, -1]

        start1, end1 = self.findLeftRightIdx(nums, target, True)
        left_idx = start1 if nums[start1] == target else end1

        start2, end2 = self.findLeftRightIdx(nums, target, False)
        right_idx = end2 if nums[end2] == target else start2

        if nums[left_idx] != target or nums[right_idx] != target:
            return [-1, -1]
        else:
            return [left_idx, right_idx]


if __name__ == "__main__":
    test = Solution()
    a = [1,1,2,3,3,3,4]
    k = 3
    print(test.searchRange(a, k))
