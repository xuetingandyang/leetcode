__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def findMaxLen(self, nums: List[int]) -> int:
        """
        for an array of only 0 or 1, find max length sub-array with equal 0 and equal 1
        eg: nums = [0,0,1,0,1,0,1]
        output: [0,1,0,1,0,1] --> len = 6
        """
        arr = [-2 for i in range(2 * len(nums) + 1)]
        arr[len(nums)] = -1

        max_len, count = 0, 0
        for i in range(len(nums)):
            count = count + (-1 if nums[i] == 0 else 1)
            if arr[count + len(nums)] >= -1:
                max_len = max(max_len, i - arr[count + len(nums)])
            else:
                arr[count + len(nums)] = i

        return max_len

    def maxLenEqual01(self, arr:List[int]) -> int:
        # find largest subarray with equal number of 0's and 1's.

        # Returns largest subarray with equal number of 0s and 1s

        # NOTE: Dictonary in python in implemented as Hash Maps.
        # Create an empty hash map (dictionary)
        hash_map = {}
        curr_sum = 0
        max_len = 0
        ending_index = -1
        n = len(arr)

        for i in range(0, n):
            if (arr[i] == 0):
                arr[i] = -1
            else:
                arr[i] = 1

            # Traverse through the given array
        for i in range(0, n):
            # Add current element to sum
            curr_sum = curr_sum + arr[i]

            # To handle sum=0 at last index
            if (curr_sum == 0):
                max_len = i + 1
                ending_index = i

            # If this sum is seen before,
            # then update max_len if required
            if curr_sum in hash_map:
                # max_len = max(max_len, i - hash_map[curr_sum])
                if i - hash_map[curr_sum] > max_len:
                    max_len = i - hash_map[curr_sum]
                    ending_index = i
            else:
                # else put this sum in dictionary
                hash_map[curr_sum] = i
        for i in range(0, n):
            if (arr[i] == -1):
                arr[i] = 0
            else:
                arr[i] = 1

        print(ending_index - max_len + 1, end=" ")
        print("to", end=" ")
        print(ending_index)

        return max_len


test = Solution()
arr = [1, 0, 0, 1, 0, 1, 1]
arr2 = [1, 1, 1, 0, 1, 0, 1, 1, 1]
print(test.findMaxLen(arr2))
print(test.maxLenEqual01(arr2))


