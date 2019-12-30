from typing import List

class Solution:
    def maxProductCutRecursive(self, n:int) -> int:
        """
        Given a rope of length n meters,
        cut the rope in different parts of integer lengths in a way that
        maximizes product of lengths of all parts.
        You must make at least one cut.
        Assume that the length of rope is more than 2 meters.

        Return the max product value.

        EG: n = 10 -> 3*3*4 is max, so output the max product = 3*3*4 = 36
        """

        if n == 0 or n ==1:
            return 0

        max_val = 0
        for i in range(1, n - 1):
            max_val = max(max_val, max(i * (n - i), i * self.maxProductCutRecursive(n - i)) )

        return max_val


    def maxProductCutDP(self, n:int) -> int:
        """
        "max Product cut" has overlapping sub-problems and optimal substructure
        we can solve it by DP (dynamic Programming)
        We use the "bottom-up" method to save solutions of sub-problems
        """
        val = [0] * (n+1) # save solutions of sub-problems
        # val[0] = val[1] = 0

        # fill the table "val"
        for i in range(2, n + 1):
            max_prod = 0
            for j in range(1, i):
                max_prod = max(max_prod, j*(i-j), j*val[i-j])
            val[i] = max_prod

        return val[n]


    def cutWood(self, L:List[int], k:int) -> int:
        """
        Given n pieces of wood with length L[i] (integer array).
        Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
        What is the longest length you can get from the n pieces of wood?
        Given L & k, return the maximum length of the small pieces.

        Notice: You couldn't cut wood into float length.
        If you couldn't get >= k pieces, return 0.

        Example
        For L=[232, 124, 456], k=7, return 114.
        """
        """
        Given a list representing the length of ribbon, and the target number "k" parts of ribbon.
        we want to cut ribbon into k parts with the same size, at the same time we want the maximum size.
        
        Ex.
        Input: A = [1, 2, 3, 4, 9], k = 5
        Output: 3
        
        Explanation:
        if size = 1, [1//1, 2//1, 3//1, 4//1, 9//1] -> sum([1, 2, 3, 4, 9]) = 19 parts
        if seize‍‍‌‌‍‌‍‌‌‍‍‍‍‍‌‌‍‍‌ = 2, [1//2, 2//2, 3//2, 4//2, 9//2] -> sum([0, 1, 1, 2, 4]) = 8 parts
        if size = 3, then we have 5 parts
        if size = 4, then we have 3 parts, which is not enough.
        So return the max size = 3.
        
        Sol.
        Use binary search to find the size of ribbon to reach the time limit.
        """
        # Binary Search
        max_l = max(L)

        left, right = 1, max_l
        result = 0
        while left <= right:
            mid = left + (right - left) // 2

            count = 0
            for num in L:
                count += num // mid
            if count >= k:
                # satisfy, increase it to get max
                left = mid + 1
                result = mid
            else:
                # too big, decrease it to satisfy "at least k"
                right = mid - 1

        return result


    def minEatSpeed(self, L:List[int], k:int) -> int:
        """
        Given n pieces of wood with length L[i] (integer array).
        Cut them into small pieces to guarantee you could have at most k pieces with as small cutting length as possible.
        Given L & k, return the max length of the small pieces. (i.e. the min cutting length -> eating speed)

        Notice: You couldn't cut wood into float length.
        If you couldn't get <= k pieces, return 0.

        Example
        For L=[3, 6, 7, 11], k=8, return 4.
        3, 4+2, 4+3, 4+4+3 --> 4
        """
        # Binary Search
        max_l = max(L)

        left, right = 1, max_l
        result = 0
        while left <= right:
            mid = left + (right - left) // 2

            count = 0
            for num in L:
                count += num // mid
                if num % mid != 0:
                    count += 1
            if count <= k:
                # satisfy, decrease it to get min
                right = mid - 1
                result = mid
            else:
                # too small, increase it to satisfy "at most k"
                left = mid + 1

        return result




test = Solution()
print(test.maxProductCutRecursive(10))  # 36
print(test.maxProductCutDP(10))
print(test.cutWood([232, 124, 456], 7)) # 114
print(test.minEatSpeed([3, 6, 7, 11], 8))  # 4


