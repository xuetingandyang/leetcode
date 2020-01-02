# Given an array A of integers, return the length of the longest arithmetic subsequence in A.
# Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k]
# with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
# and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
#
# Example 1:
# Input: [3,6,9,12]
# Output: 4
# Explanation:
# The whole array is an arithmetic sequence with steps of length = 3.

# Input: [20,1,15,3,10,5,8]
# Output: 4
# Explanation:
# The longest arithmetic subsequence is [20,15,10,5].
# Note:
# 2 <= A.length <= 2000
# 0 <= A[i] <= 10000

from typing import List


class Solution:
    def longestArithSeqLength(self, A: List[int]) -> int:
        # dynamic programming, save tuple(Arithmetic sequence "end idx", length) as key for hashmap "dp = {}"
        # 
        if len(A) < 2 or len(A) > 2000:
            return -1
        for num in A:
            if num < 0 or num > 10000:
                return -1

        dp = {}
        for j, a2 in enumerate(A[1:], start=1):
            for i, a1 in enumerate(A[:j]):
                d = a2 - a1
                if (i, d) not in dp:
                    dp[j, d] = 2  # arithmetic seq end with idx 'j', len = 2
                else:
                    dp[j, d] = dp[i, d] + 1
                    # arithmetic seq end with idx 'j', len = end with 'i' + 1
        maxLen = max(dp.values())
        # for key in dp.keys():
        #     if dp[key] == maxLen:
        #         end, diff = key
        #
        # start_val = A[end] - (maxLen - 1) * diff
        # start = A.index(start_val)
        # print("start from A[{0}] to A[{1}]".format(start, end))
        return maxLen


if __name__ == "__main__":
    test = Solution()
    A = [20, 1, 15, 3, 10, 5, 8]     # 4: [20, 15, 10, 5]
    print(test.longestArithSeqLength(A))
