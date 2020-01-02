__author__ = ' Zhen Wang'
from typing import List


class Solution:

    def checkDiff(self, diff: int, a: List[int]):
        # check if 'a' is an arithmetic array with difference = 'diff'
        for num in a:
            if diff == 0:
                if num != a[0]:
                    return False

            elif (num - a[0]) % diff != 0:
                return False
        return True

    def countMaxArithLen(self, a: List[int], b: List[int]):
        """
        find the maximal length of the resulting arithmetic progression
        represented by array "a" that can be achieved.

        If is is impossible to obtain an array forming an arithmetic progression, return -1

        EG: for a = [0, 4, 8, 16] and b = [0, 2, 4, 12, 14, 20], maxArithmeticLength(a, b) = 6.
        Ans: add b[3] = 12 and b[5] = 20 to "a" and obtain array [0, 4, 8, 12, 16, 20] with max length = 6
        output: 6

        for a = [5, 7, 13, 14] and b = [9, 11, 15]
        output: -1
        """

        a = sorted(a)
        b = sorted(b)

        diffs = []  # save possible differences
        if len(a) > 1 and self.checkDiff(a[1] - a[0], a):
            # if 'a' is an arithmetic array
            diffs.append(a[1] - a[0])

        if len(a) == 1:
            # # possible diff = unique number in 'b' - a[0]
            # for num in set(b):
            #     diffs.append(num - a[0])
            #
            # for diff in diffs:
            c = a + b
            c.sort()
            dp = {}
            for i, a2 in enumerate(c[1:], start=1):
                for j, a1 in enumerate(c[:i]):
                    d = a2 - a1
                    if (j, d) in dp:
                        dp[i, d] = dp[j, d] + 1
                    else:
                        dp[i, d] = 2
            for key in dp.keys():
                if dp[key] == max(dp.values()):
                    startIdx, diff = key
                    arr = c[startIdx ]





        else:
            for num in b:
                if num > a[0] and num < a[1]:
                    # save to possible diff only if a[0] < num < a[1]
                    if self.checkDiff(num - a[0], a):
                        diffs.append(num - a[0])
                if num >= a[1]:
                    break

        if len(diffs) == 0:
            return -1
        # finish searching the possible differences

        maxInsertNum = -1

        for diff in diffs:
            if diff == 0:
                if self.checkDiff(diff, a):  # each element in 'a' is SAME
                    return len(a) + b.count(a[0])  # maxLen = len(a) + number of a[0] in 'b'
            else:
                endIdx = (a[-1] - a[0]) // diff
                insertNum = 0
                for i in range(endIdx):
                    # search possible num in "b" to insert between a[0], a[-1]
                    if (a[0] + i * diff) not in a:  # set a
                        if (a[0] + i * diff) not in b:  # set b
                            break
                        else:
                            insertNum += 1
                idx = 1
                while a[0] - idx * diff >= b[0]:
                    # search possible num in "b" to insert before a[0]
                    tmp = a[0] - idx * diff
                    if tmp not in a and tmp in b:
                        insertNum += 1
                    else:
                        break
                    idx += 1

                idx = 1
                while a[-1] + idx * diff <= b[-1]:
                    # search possible num in "b" to insert after a[-1]
                    tmp = a[-1] + idx * diff
                    if tmp not in a and tmp in b:
                        insertNum += 1
                    else:
                        break
                    idx += 1

            if maxInsertNum < insertNum:
                maxInsertNum = insertNum

        return maxInsertNum + len(a)


sol = Solution()
# a = [0, 4, 8, 16]
# b = [0, 2, 4, 12, 14, 20]  # 6  = [0,4,8,12,16,20]
# print(sol.countMaxArithLen(a, b))
# a = [7, 13]
# b = [1, 10, 16]  # 4 = [7, 10, 13, 16]
# print(sol.countMaxArithLen(a, b))
# a = [20, 22]
# b = [19, 21, 23, 24, 26, 28]  # 6 = [19, 20, 21, 22, 23, 24]
# print(sol.countMaxArithLen(a, b))
# a = [5, 6]
# b = [2, 3, 4, 6, 7, 8, 28]  # 6 = [2,3,4,5,6,7,8]
# print(sol.countMaxArithLen(a, b))
# a = [5, 5]
# b = [2, 3, 4, 5, 5, 5, 7, 8, 28]
# print(sol.countMaxArithLen(a, b))
a = [5]
b = [2, 3, 4, 5, 5, 7, 8, 28]
print(sol.countMaxArithLen(a, b))