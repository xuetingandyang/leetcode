__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def sum(self, a: List[int], b: List[int]) -> int:
        """
        eg: a = [12, 30], b = [112, 8]
        a[0] + b[-1] = "128"
        a[1] + b[-2] = "30112"
        add: 128 + 30112 = 30240
        """
        rst = 0

        if len(a) != len(b):
            return rst

        for i in range(len(a)):
            rst += self.concatenate(a[i], b[len(b) - i - 1])

        return rst

    def concatenate(self, n1: int, n2: int) -> int:
        """
        eg: 30 + 12 = 3012
        """
        n3 = n2
        while n2 != 0:
            n1 = n1 * 10
            n2 //= 10
        return n1 + n3


if __name__ == "__main__":
    sol = Solution()

    a = [12, 30]
    b = [112, 8]

    print(sol.sum(a, b))

