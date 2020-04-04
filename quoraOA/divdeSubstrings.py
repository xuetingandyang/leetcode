__author__ = ' Zhen Wang'


class Solution:

    def divide(self, number: int, k: int) -> int:
        """
        eg: number = 120, k = 2.
        divide number into sub-string with length = k
        return the number of substrings that number % sub-string == 0

        """
        s = str(number)
        st = []
        for i in range(len(s) - k + 1):
            subStr = s[i: i + k]
            num = int(subStr)
            if num != 0 and number % num == 0:
                st.append(num)
        return len(st)


# if __name__ == "__main__":
sol = Solution()
number = 22220
k = 2
print(sol.divide(number, k))
print(sol.divide(420020, 2))