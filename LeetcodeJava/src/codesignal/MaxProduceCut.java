package codesignal;

//  Given a rope of length n meters,
//    cut the rope in different parts of integer lengths in a way that
//    maximizes product of lengths of all parts.
//    You must make at least one cut.
//    Assume that the length of rope is more than 2 meters.
//    Return the max product value.
//    EG: n = 10 -> 3*3*4 is max, so output the max product = 3*3*4 = 36
//
//    "max Product cut" has overlapping sub-problems and optimal substructure
//    we can solve it by DP (dynamic Programming)
//    We use the "top-bottom" method to save solutions of sub-problems

public class MaxProduceCut {
    public int maxProductCutDP(int n) {
        int[] dp = new int[n+1];

        for (int i = 2; i < n + 1; i ++) {
            int maxProd = Integer.MIN_VALUE;
            for (int j = 1; j < i; j ++) {
                maxProd = Math.max(maxProd, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = maxProd;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        MaxProduceCut obj = new MaxProduceCut();
        int rst = obj.maxProductCutDP(12);
        System.out.println(rst);
    }
}
