__author__ = ' Zhen Wang'

from collections import Counter


class Solution:

    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str

        s = "tree"
        sort "s" by frequency --> 'eetr'
        """
        c1, c2 = Counter(s), {}
        for k,v in c1.items():
            c2.setdefault(v, []).append(k*v)
        return "".join(["".join(c2[i]) for i in range(len(s), -1, -1) if i in c2])


sol = Solution()
s = "tree"
print(sol.frequencySort(s))