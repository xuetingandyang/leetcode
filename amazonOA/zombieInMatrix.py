# Given a 2D grid, each cell is either a zombie 1 or a human 0.
# Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour.
# Find out how many hours does it take to infect all humans?

# Input:
# [[0, 1, 1, 0, 1],
#  [0, 1, 0, 1, 0],
#  [0, 0, 0, 0, 1],
#  [0, 1, 0, 0, 0]]
# Output: 2
#
# Explanation:
# At the end of the 1st hour, the status of the grid:
# [[1, 1, 1, 1, 1],
#  [1, 1, 1, 1, 1],
#  [0, 1, 0, 1, 1],
#  [1, 1, 1, 0, 1]]
# At the end of the 2nd hour, the status of the grid:
# [[1, 1, 1, 1, 1],
#  [1, 1, 1, 1, 1],
#  [1, 1, 1, 1, 1],
#  [1, 1, 1, 1, 1]]

from typing import List
from collections import defaultdict, deque

class Solution:
    def minHours(self, grid: List[List[int]]) -> int:
        """
        For each Zombie(1), use BFS to search all neighbors(can be affected)
        Mark all neighbors to Zombie.
        Until all matrix is Zombie
        """
        if not grid:
            return -1

        m, n = len(grid), len(grid[0])
        ZOMBIE, HUMAN = 1, 0
        directions = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        count, target = 0, m * n   # count number of hours, target number of ZOMBIE

        # queue to keep track of all ZOMBIE
        # then each node in queue, BFS to affect its neighbors
        queue = deque([(i, j) for i in range(m) for j in range(n) if grid[i][j] == ZOMBIE])
        cur_zombie = len(queue)

        while queue:

            if cur_zombie == target:
                return count

            for _ in range(len(queue)):
                ii, jj = queue.popleft()
                for dx, dy in directions:
                    x = ii + dx
                    y = jj + dy
                    if 0 <= x <= m-1 and 0 <= y <= n-1 and grid[x][y] == HUMAN:
                        grid[x][y] = ZOMBIE     # become ZOMBIE
                        queue.append((x, y))    # NEW ZOMBIE
                        cur_zombie += 1         # update current number of zombies
            count += 1  # count hours

        return -1


if __name__ == "__main__":
    test = Solution()
    grid = [[0, 1, 1, 0, 1], [0, 1, 0, 1, 0], [0, 0, 0, 0, 1], [0, 1, 0, 0, 0]]
    print(test.minHours(grid))




