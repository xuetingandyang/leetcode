package codesignal;

import java.util.*;

//Similar to leetcode 49
//Given an array of numbers, find the largest anagrams.
//
//Input: [123,321,132,224,422],
//Anagrams:
//[
//    [123, 321, 132],
//    [224, 422],
//]
//Output: length of [123, 321, 132] = 3

// Solution: anagrams are same after sort
// use hashmap (num, freq), where num = sorted numbers

public class DigitAnagrams {

    private int getAnangram(int num) {
        // convert 132, 321 to 123
        char[] numChars = String.valueOf(num).toCharArray();
        Arrays.sort(numChars);
        return Integer.parseInt(new String(numChars));
    }

    public int findAnagrams(int[] nums){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int anagramNum = getAnangram(num);
            map.putIfAbsent(anagramNum, new ArrayList<>());
            map.get(anagramNum).add(num);
        }
        int result = 0;
        for (int num : map.keySet()) {
            result = Math.max(result, map.get(num).size());
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = {123,321,132,224,422, 768, 876, 786, 978};
        DigitAnagrams digitAnagrams = new DigitAnagrams();
        int rst = digitAnagrams.findAnagrams(nums);
        System.out.println(rst);
    }
}
