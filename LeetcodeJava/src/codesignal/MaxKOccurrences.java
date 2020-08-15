package codesignal;

// input: a sequence and words
// output: max k occurrences

// sequence="ababcbabc", words=["ab", "babc", "bca"]
// output=[2,2,0]
// for "ab", max occurrences in sequence is "abab"     => 2
//      "babc"                              "babcbabc" => 2
//      "bca", not appear in sub-sequences             => 0

import java.util.*;

public class MaxKOccurrences {

    private List<Integer> findOccIdx(String word, String seq) {
        int len = word.length();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < seq.length() - len + 1; i ++) {
            String subSeq = seq.substring(i, i + len);
            if (subSeq.equals(word)) {
                indices.add(i);
            }
        }
        return indices;
    }

    private int maxArithmeticLen(List<Integer> nums, int diff) {
        // find maxLength of arithmetic seq with diff
        int maxLen = 1;
        for (int i = 0; i < nums.size(); i ++) {
            int len = 1;
            for (int j = i + 1; j < nums.size(); j ++) {
                if (nums.get(j) - nums.get(i) == len * diff) {
                    len++;
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    public int[] maxKOcc(String seq, String[] words) {
        int[] occ = new int[words.length];
        // get the list of occurred indices of each word
        for (int i = 0; i < words.length; i ++) {
            String word = words[i];
            List<Integer> indices = findOccIdx(word, seq);
            // if not occur, the maxK Occurrence is 0
            if ( indices.size() == 0 ) {
                occ[i] = 0;
                break; // go to next word
            }
            // find the longest Arithmetic sequences with diff = word.length()
            occ[i] = maxArithmeticLen(indices, word.length());
        }
        return occ;
    }

    public static void main(String[] args) {
        String seq = "ababcbabc";
        String[] words = {"ab", "babc", "bca",};
        MaxKOccurrences obj = new MaxKOccurrences();
        int[] occ = obj.maxKOcc(seq, words);
        System.out.println(occ);
//        System.out.println(obj.mxArithmeticLen(Arrays.asList(1,2,5,6,7,8), 1));
    }
}
