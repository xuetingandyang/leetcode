from typing import List

class Solution:
    def removeOneDigit(self, s:str, t:str) -> int:
        """
        remove one digit char from string s or t, so that s < t;
        output the number of ways to remove the character from the string.
        """
        count = 0
        for i in range(len(s)):
            if s[:i] + s[i + 1:] > t:
                # print(s[:i] + s[i + 1:],  t)
                count = count + 1
        for j in range(len(t)):
            if s > t[:j] + t[j + 1:]:
                # print(s, t[:j] + t[j + 1:])
                count = count + 1

        return count

test = Solution()
print(test.removeOneDigit("1123", "02311"))
print(test.removeOneDigit("12345", "1078201"))

