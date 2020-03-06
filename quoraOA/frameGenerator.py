from typing import List

class Solution:
    def frameGenerator(self, n:int) -> List[List[bool]]:
        if n == 1:
            return [[True]]

        frame = [[False for i in range(n)] for j in range(n)]
        # for i in range(n):
        # frame[0], frame[n-1] = [True] * n, [True] * n

        for i in range(n):
            for j in range(n):
                if i == 0 or i == n-1 or j == 0 or j == n-1:
                    frame[i][j] = True
        return frame

# if __name__ == "main":
test = Solution()
frame = test.frameGenerator(5)

