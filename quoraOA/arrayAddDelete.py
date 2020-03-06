from typing import List
class Solution:
    def arrayAddDelete(self, arr:List[int]) -> int:
        result = arr[0]
        for i in range(1, len(arr)):
            result += pow(-1, i) * arr[i]
        return result


    def intAddDelete(self, n:int) -> int:
        strN = str(n)
        result = int(strN[0])
        for i in range(1, len(strN)):
            result += pow(-1, i) * int(strN[i])

        return result

    def mostCommonItem(self, nums:List[int]) -> List[int]:
        """
        Input: A = [2, 2, 3, 3, 5]
        Output: [2, 3]

        Sol: Use a dictionary and keep track of the largest frequency.
        """
        dict = {}
        max_freq = 0
        result = []
        for num in nums:
            if num in dict:
                dict[num] += 1
                if dict[num] > max_freq:
                    max_freq = dict[num]
            else:
                dict[num] = 1
        for num in dict.keys():
            if dict[num] == max_freq:
                result.append(num)
        return result





test = Solution()
print(test.arrayAddDelete([5,4,3,1,5]))
print(test.intAddDelete(54315))
print(test.mostCommonItem([1,2,3,4,2,4,5,5]))