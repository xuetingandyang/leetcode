// Given a string s, partition s such that every substring of the partition is a palindrome.
// Palindrome: a sequence that reads the same backward as forward,
//    Return all possible palindrome partitioning of s.

//    Input: "aab"
//    Output:
//    [
//    ["aa","b"],
//    ["a","a","b"]
//    ]

import java.util.ArrayList;
import java.util.List;

/* All partitions are combination problems
* abc   =  a1b2c
* a,b,c => [1,2]
* a, bc => [1]
* ab, c => [2]
* abc   => []
* n-chars string => (n-1) numbers array
*
* Time: O(2^n * n^2)
* for a string with length n, there are (n-1) intervals between chars
* for each interval, we can cut or not cut it.
* there are 2^(n-1) ways to partition the string
*
* for each partition, we need to check if it is palindrome O(n)
* and make a copy of the partition into the output (deep copy) O(n)
* so
*
* */
public class PalindromePartitioning {
    boolean[][] isP;

    public List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return rst;
        }

        getPalindrome(s);

        backtrack(s, rst, new ArrayList<>(), 0);
        return rst;
    }

    // 1-recursion definition
    // Find all combinations that starts with 'combination',
    // and possible elements are s[startIndex, i + 1].

    // only keep the combinations with all elements: a, b, c.
    // only keep palindrome combinations
    public void backtrack(String s,
                          List<List<String>> rst,
                          List<String> combination,
                          int startIndex) {

        // 3-recursion exit
        // when startIndex == s.length -> combination contains a, b, c.
        if (startIndex == s.length()) {
            // deep copy
            rst.add(new ArrayList<>(combination));
            return;
        }

        // 2-recursion split
        for (int i = startIndex; i < s.length(); i ++) {
            String subString = s.substring(startIndex, i + 1);

            // 2 ways to valid palindrome
//            if (! isPalindrome(subString)) continue;  // not break
            if (! isP[startIndex][i]) continue;

            combination.add(subString);
            backtrack(s, rst, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    public boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0, j = len - 1; (i < len) && (j > 0); i ++, j --) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    /* Optimize function that decide the palindrome matrix before recursion
    * so time complexity before deep copy can be reduced from O(2^(n-1) * n) to O(2^(n-1) + n^2) = O(2^n)
    * therefore, total time complexity from O(2^n * n^2) -> O(2^n * n)
    *
    * for string 'efettc'
    * isP[0][2]=true represents 'efe' is palindrome.
    * for isP matrix, lower triangle of matrix is always false. (isP[2][0] makes no sense)
    * Case1: isP[i][i] = true
    * Case2: isP[i][i+1] = check neighbor two chars, like 'ef', 'fe' -> s.charAt[i] == s.charAt[i+1]
    *
    * Case3: compute from end to top, otherwise you may encounter the situation that isP[i+1][j-1] has not updated.
    * so for i = n-2, for j = i + 2.
    * isP[i][j] = middle string is palindrome + same head and tail
    * isP[i][j] = isP[i + 1][j - 1] && string[i] == string[j]
    * */

    public void getPalindrome(String s) {
        int n = s.length();
        isP = new boolean[n][n];

        for (int i = 0; i < n; i ++) {
            isP[i][i] = true;
        }

        for (int i = 0; i < n - 1; i ++) {
            isP[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int i = n - 2; i >= 0; i --) {
            for (int j = i + 2; j < n; j ++) {
                 isP[i][j] = isP[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
    }


    public static void main(String[] args) {
        String s = "cbbbcc";
        PalindromePartitioning Solution = new PalindromePartitioning();
        System.out.println(Solution.partition(s));

    }

}
