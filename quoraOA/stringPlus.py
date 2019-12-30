from typing import List

class Solution:
    def stringAdd(self, s1:str, s2:str) -> str:
        """
        eg: "99" + "99" = "1818"
        "99" + "1" = "910"
        """
        sum = str()
        i, j = len(s1) - 1, len(s2) - 1
        while i >= 0 or j >= 0:
            num1, num2 = 0, 0   # if another string did not have number, set it to 0
            if i >= 0:
                num1 = int(s1[i])
            if j >= 0:
                num2 = int(s2[j])
            sum = str(num1 + num2) + sum
            i -= 1
            j -= 1

        return sum

test = Solution()
res = test.stringAdd("18", "8")