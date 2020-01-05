from typing import List


class Solution_wrong:
    def searchRow(self, image: List[List[str]], start: int, end: int, left: int, right: int, blackorwhite: bool) -> int:
        # find first appeared element index
        # left idx: find 1-st 'black'
        # right idx: 1-st 'non-black' - 1 (start with given black element location)
        while start + 1 < end:
            mid = start + (end - start) // 2
            count = left
            for k in range(left, right):
                if image[mid][k] == '0':
                    count += 1
            if count < right and blackorwhite:
                # for blackorwhite='True': means in [left, right], exist '1'
                # for .............'False' means ................. all chars are '0'
                end = mid
            else:
                start = mid
        print("search row ", start, end)

        # for j in range(left, right):
        #     if blackorwhite and image[start][j] == '1':
        #         return start
            # if not blackorwhite
            #     return start
        if ('1' in image[start][left:right] and blackorwhite) or \
                ('1' not in image[start][left:right] and not blackorwhite):
            return start
        return end

    def searchCol(self, image: List[List[str]], start: int, end: int, top: int, bottom: int, blackorwhite: bool) -> int:
        # find first appeared element index
        # left idx: find 1-st 'black'
        # right idx: 1-st 'non-black' - 1 (start with given black element location)
        while start + 1 < end:
            mid = start + (end - start) // 2
            count = top
            for k in range(top, bottom):
                if image[k][mid] == '0':
                    count += 1
            if count < bottom and blackorwhite:
                # for blackorwhite='True': means in [left, right], exist '1'
                # for .............'False' means ................. all chars are '0'
                end = mid
            else:
                start = mid
        print("search col ", start, end)
        count = top
        for k in range(top, bottom):
            if image[k][start] == '0':
                count += 1
        if (count < bottom and blackorwhite) or (count == bottom and not blackorwhite):
            # 1. blackorwhite==True and contain '1'
            # 2. blackorwhite==False and all chars are '0'
            return start
        return end

    def minArea(self, image: List[List[str]], x: int, y: int) -> int:
        m, n = len(image), len(image[0])
        if m == 1:
            count = 0
            for k in range(n):
                if image[0][k] == "1":
                    count += 1
            return count
        if n == 1:
            count = 0
            for k in range(m):
                if image[k][0] == "1":
                    count += 1
            return count
        top_idx = self.searchRow(image, 0, x, 0, n, True)
        bottom_idx = self.searchRow(image, x + 1, m, 0, n, False) - 1
        left_idx = self.searchCol(image, 0, y, top_idx, bottom_idx + 1, True)
        right_idx = self.searchCol(image, y + 1, n, top_idx, bottom_idx + 1, False) - 1
        print(top_idx, bottom_idx, left_idx, right_idx)
        return (bottom_idx - top_idx + 1) * (right_idx - left_idx + 1)




class Solution:
    def findOne(self, image:List[List[int]], row:bool, idx:int) -> bool:
        if row:
            for num in image[idx]:
                if num == "1":
                    return True
        else:
            for i in range(len(image)):
                if image[i][idx] == "1":
                    return True
        return False

    def minArea(self, image:List[List[str]]) -> int:
        # find the 4 indices.
        # (left, right) = 1-st col has "1", last col has "1"
        # (top, bottom) = 1-st row ............. row ......
        # time: O(mn), space: O(1)
        # do not need to know the location of one of the black("1") elements

        m, n = len(image), len(image[0])
        left, right, top, bottom = float("inf"), 0, float("inf"), 0
        for i in range(m):
            if self.findOne(image, True, i):
                top = min(top, i)
                bottom = max(bottom, i)
        for j in range(n):
            if self.findOne(image, False, j):
                left = min(left, j)
                right = max(right, j)
        print(left, right, top, bottom)
        return (bottom - top + 1) * (right - left + 1)




if __name__ == "__main__":
    test = Solution()
    a = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]]
    # x = 0
    # y = 2
    print(test.minArea(a))
    a = [["0", "1", "0"]]
    x = 0
    y = 1
    print(test.minArea(a))
    a = [["0","1"],["0","1"]]
    x = 0
    y = 1
    print(test.minArea(a))