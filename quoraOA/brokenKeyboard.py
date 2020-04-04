__author__ = ' Zhen Wang'

from typing import List


class Solution:

    def numOfWords(self, s: str, keys: List[str]) -> int:
        """
        broken keybord 键盘的部分英文字母键坏了（注意只有字母键坏了）
        给定一个String 和 一个char Array（没坏的字母键），输出String中能打出的字符串数。

        input “hello, world!” ['i','e','o','l','h'];
        output: 1 (只能打出 hello 这个单词）

        input “5 + 3 = 8” [];
        output: 5 (没有英文字母， 5， +， 3， =， 8 都可以打出）

        """
        if s is None or len(s) == 0:
            return 0

        st = set()
        for key in keys:
            st.add(key)

        words = s.split(" ")
        rst = 0

        for word in words:
            flag = True     # if the character can be typed
            for c in word:
                if c.isalpha() and c not in st: # char is "broken"
                    flag = False
                    break   # in this word, there is a char cannot be typed, thus break into next word
            if flag:
                rst += 1

        return rst


if __name__ == "__main__":
    sol = Solution()
    s = "hello world lio"
    keys = ["i", "e", "o", "l", "h"]
    print(sol.numOfWords(s, keys))

    s = "1 + 2 = 3"
    k = []
    print(sol.numOfWords(s, k))



