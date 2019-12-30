__author__ = ' Zhen Wang'
from typing import List


class Solution:

    def countTarget(self, a: List[int], b: List[int], target: int):
        rst = 0

        for A in a:
            for B in b:
                if A + B == target:
                    rst += 1
        return rst

    def findFeature(self, a: List[int], b: List[int], query: List[List[int]]):
        """
        a = [1, 2, 3]
        b = [3, 4]
        query = [
            [1, 5],
            [1, 1, 1],
            [1, 5]
        ]

        [1,5]: target = 5, find n1 in a, n2 in b, to make n1+n2=target,
        output:number of possible (n1, n2) paris
        [1,1,1]: change b[0] to 1


        """
        rst: List[int] = []
        for list in query:
            if len(list) == 2:
                target = list[1]
                record = self.countTarget(a, b, target)
                rst.append(record)
            elif len(list) == 3:
                b[list[1] - 1] = list[2]

        return rst


if __name__ == "__main__":
    sol = Solution()

    a = [1, 2, 3]
    b = [3, 4]
    query = [
        [1, 5],
        [1, 1, 1],
        [1, 5]
    ]

    print(sol.findFeature(a, b, query))
