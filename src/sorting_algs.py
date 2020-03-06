""""
10 traditional sorting algorithms
"""

from typing import List


class Solution:

    def bubble_sort(self, nums: List[int]) -> List[int]:
        if len(nums) < 1 or len(nums) > 50000:
            return []

        def swap(i, j):
            nums[i], nums[j] = nums[j], nums[i]
        swapped = True
        x = -1

        # if no swap in this for loop (swapped=False), break
        while swapped:
            swapped = False
            x += 1
            for i in range(1, len(nums) - x):
                if nums[0] < -50000 or nums[0] > 50000:
                    return []
                if nums[i] < -50000 or nums[i] > 50000:
                    return []

                if nums[i-1] > nums[i]:
                    swap(i-1, i)
                    swapped = True

        return nums


    def selection_sort(self, nums: List[int]) -> List[int]:

        for i in range(len(nums)):
            min_idx = i
            for j in range(i+1, len(nums)):
                if nums[j] < nums[min_idx]:
                    min_idx = j

            # swap the minimum with the nums[i]
            nums[i], nums[min_idx] = nums[min_idx], nums[i]

        return nums


if __name__ == "__main__":
    nums = [1, 6, 7, 8, 2, 2, 3, -4, -3]
    Test = Solution()
    print(Test.bubble_sort(nums))
    print(Test.bubble_sort(nums))

