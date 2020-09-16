package slidingwindow;
//Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
//    Example 1:
//
//    Input: s = "eceba", k = 2
//    Output: 3
//    Explanation: T is "ece" which its length is 3.
//    Example 2:
//
//    Input: s = "aa", k = 1
//    Output: 2
//    Explanation: T is "aa" which its length is 2.


import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int maxLen = 0;

        // (char, freq) in current window
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        while (right < s.length()) {
            // add 'right' to window
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (window.size() <= k && right - left + 1 > maxLen) {
                maxLen = right - left + 1;
            }
            // System.out.println(window);
            // try to shrink
            while (left <= right && window.size() > k) {
                c = s.charAt(left);
                // remove 'left' from window
                window.put(c, window.get(c) - 1);
                if (window.get(c) == 0) {
                    window.remove(c);
                }
                left ++;
            }
            right ++;
        }
        return maxLen;
    }
}
