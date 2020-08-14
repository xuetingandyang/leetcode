package codesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//1027. Longest Arithmetic Sequence
// Given an array A of integers, return the length of the longest arithmetic subsequence in A.
// Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
// and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

//    Input: [3,6,9,12]
//    Output: 4
//    Explanation:
//    The whole array is an arithmetic sequence with steps of length = 3.

//    Input: [9,4,7,2,10]
//    Output: 3
//    Explanation:
//    The longest arithmetic subsequence is [4,7,10].

//    Input: [20,1,15,3,10,5,8]
//    Output: 4
//    Explanation:
//    The longest arithmetic subsequence is [20,15,10,5].


public class LongestArithmeticSequence {

    public int longestArithSeqLengthDP(int[] A) {
        // dp to record the length of arithmetic sequence at index i with difference diff.
        // dp[i] = (diff, length)
        int res = 2, n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < A.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                res = Math.max(res, dp[i].get(diff));
            }
        }
        return res;
    }

    public int longestArithSeqLength(int[] A) {
        int rst = Integer.MIN_VALUE;
        int[][] mem = new int[A.length][A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A.length; j++){
                if(mem[i][j] == 0){
                    int diff = A[j] - A[i];
                    int start = i, end = j;
                    mem[i][j] = 2;
                    for(int k = j + 1; k < A.length; k++){
                        if(A[k] == A[end] + diff){
                            mem[end][k] = mem[start][end] + 1;
                            start = end;
                            end = k;
                        }
                    }
                    int count = mem[start][end];
                    rst = count > rst ? count : rst;
                }

            }
        }
        return rst;

    }



}
