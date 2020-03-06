from typing import List

class Solution:
    def numEvenDigit(self, nums:List[int]) -> int:
        """
        Find how many numbers have even digit in a list.
        Ex.
        Input: A = [12, 3, 5, 3456]
        Output: 2

        Sol.
        Use a for loop
        """
        count = 0
        for num in nums:
            for char in str(num):
                if int(char) % 2 == 0:
                    count += 1
                    break
        return count

test = Solution()
print(test.numEvenDigit([13456, 2456, 661, 135, 235]))

