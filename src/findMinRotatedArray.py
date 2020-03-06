# Suppose a sorted array in ascending order is rotated at some pivot unknown to you beforehand.
# (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
# Find the minimum element.

# Example 1:
# Input：[4, 5, 6, 7, 0, 1, 2]
# Output：0
# Explanation：
# The minimum value in an array is 0.


# 1. Brute Force is O(n), want better --> binary search
# 2. find "o": > last num
#         "x": <= last num
# 3. find fist "x" = find min --> find first num <= last num

class Solution:
    """
    @param nums: a rotated sorted array
    @return: the minimum number in the array
    """

    def findMin(self, nums):
        start, end = 0, len(nums) - 1

        while start + 1 < end:
            mid = start + (end - start) // 2
            if nums[mid] <= nums[-1]:
                end = mid
            else:
                start = mid

        # if nums[start] == nums[end]:
        #     return start
        if nums[start] >= nums[end]:
            return nums[end]
        else:
            # nums[start] < nums[end]:
            return nums[start]


if __name__ == "__main__":
    test = Solution()
    a = [4,5,1,2,3]
    print(test.findMin(a))
    a = [1,2,3,4]
    print(test.findMin(a))
    a = [4,5,6,7,1]
    print(test.findMin(a))