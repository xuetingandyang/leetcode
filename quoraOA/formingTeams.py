__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def formTeam(self, k: int, players: List[int]):
        """
        k = 0
        players = [-1, 0, 1, 2, -1, -4]

        find three players in "players" to make sum = k
        (three sum)
        """

        sortedIdx = [i[0] for i in sorted(enumerate(players), key=lambda x: x[1])]
        players = sorted(players)

        rst = []
        n = len(players)

        for i in range(n):
            target = k - players[i]
            l, h = i + 1, n - 1
            while l < h:
                if players[l] + players[h] == target:
                    tmpSet = set()
                    tmpSet.add(sortedIdx[i])
                    tmpSet.add(sortedIdx[l])
                    tmpSet.add(sortedIdx[h])
                    rst.append(tmpSet)
                    if l + 1 < h and players[l] == players[l + 1]:
                        l += 1
                    elif h - 1> l and players[h - 1] == players[h]:
                        h -= 1
                    else:
                        l += 1
                        h -= 1
                elif players[l] + players[h] > target:
                    h -= 1
                else:
                    l += 1
        return rst


sol = Solution()
k = 0
players = [-1, 0, 1, 2, -1, -4]
print(sol.formTeam(k, players))