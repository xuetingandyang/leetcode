__author__ = ' Zhen Wang'


class Solution:

    def check(self, a: str, b:str) -> bool:
        """
        ?: If two strings are close enough.
        Given two rules to define two strings are close enough.
        1. you can swap neighbor char any times. Ex. "abb" -> "bba"
        2. If two strings have the same character, then you can change the character into another.
            Ex. If both strings contain "a" and "b", you can change all "a"s in the first string or change all "b"s in the first string. same as the second string

        Ex.
        Input: S1 = "babzccc", S2 = "abbzczz"
        Output: True
        """
        if a is None and b is None:
            return True

        if a is None or b is None:
            return False

        if len(a) != len(b):
            return False

        map_a = {}
        map_b = {}

        for c in a:
            map_a[c] = map_a[c] + 1 if c in map_a else 1

        for c in b:
            map_b[c] = map_b[c] + 1 if c in map_b else 1

        # check if 'a' and 'b' contain SAME characters
        for k in map_a:
            if k not in map_b:
                return False

        # count frequency, if under SAME freq, have SAME characters --> True
        count_a = {}
        count_b = {}

        for _, freq in map_a.items():
            count_a[freq] = count_a[freq] + 1 if freq in count_a else 1

        for _, freq in map_b.items():
            count_b[freq] = count_b[freq] + 1 if freq in count_b else 1

        for k in count_a:
            if count_a[k] != count_b[k]:
                return False

        return True


if __name__ == "__main__":
    sol = Solution()

    a = "babzccc"
    b = "abbzczz"

    print(sol.check(a, b))

