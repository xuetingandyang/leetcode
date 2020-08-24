package dp;

//You have a number of envelopes with widths and heights given as a pair of integers (w, h).
// One envelope can fit into another
// if and only if both the width and height of one envelope is greater than
// the width and height of the other envelope.
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//    Note:
//    Rotation is not allowed.
//
//    Input: [[5,4],[6,4],[6,7],[2,3]]
//    Output: 3
//    Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

import java.util.Arrays;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        // sort envelops by index 0 & index 1
        // dp[i]: maxLength of Russian doll ended with envelopes[i]
        // dp[i] = max(dp[k], k in [0, i-1], && (i,0)>(k,0) && (i,1)>(k,1)) + 1

        if (envelopes == null || envelopes.length == 0) return 0;

        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = envelopes.length;
        int[] dp = new int[n];

        dp[0] = 1;

        for (int i = 0; i < n; i ++) {
            int maxLen = 0;
            for (int k = 0; k < i; k ++) {
                if (envelopes[i][0] > envelopes[k][0] && envelopes[i][1] > envelopes[k][1]) {
                    maxLen = Math.max(maxLen, dp[k]);
                }
            }
            dp[i] = maxLen + 1;
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
