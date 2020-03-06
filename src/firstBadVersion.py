# class SVNRepo:
#    @classmethod
#    def isBadVersion(cls, id)
#        # Run unit tests to check whether verison `id` is a bad version
#        # return true if unit tests passed else false.
# You can use SVNRepo.isBadVersion(10) to check whether version 10 is a
# bad version.

## Binary Search
class Solution:
    """
    @param n: An integer
    @return: An integer which is the first bad version.
    """

    def findFirstBadVersion(self, n):
        # write your code here
        start, end = 0, n
        rst = n
        while start + 1 < end:
            mid = start + (end - start) // 2
            if SVNRepo.isBadVersion(mid):
                end = mid
                if rst > mid:
                    rst = mid
            else:
                start = mid
        return rst

# this cannot run on my computer, but can run in "lintcode"
# 1. while: start + 1< end : should not change
# 2. end, start, update +1, -1: just leave it to "mid" to avoid error. only increase 2 trys
# 3. Note: rst = mid. and start = 0 (for length=2 still can work for "while condition")