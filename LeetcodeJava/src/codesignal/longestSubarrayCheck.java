package codesignal;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// int[] a, int[] b, int[] c
// 1. each element of b belongs to c
// 2. a contains b as a contiguous subarray
// 3. b is the longest contiguous subarray that satisfied 1,2

// a=[1,1,5,1,2], b = [1,2], c=[2,1] => return true
// a=[1,2,3,6,1,1], b=[1,2,3], c=[2,1] => return false, '1' is not satisfied

public class longestSubarrayCheck {

    private String intArrayToStr(int[] a){
        return IntStream.of(a)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(""));
    }

    public boolean longestSubarrayCheck(int[] a, int[] b, int[] c){
        if (a.length == 0 || b.length == 0 || c.length == 0) return false;

        Set<Integer> setC = new HashSet<>();
        for (int c_item: c) setC.add(c_item);
        // requirement-1
        for(int b_item: b){
            if( ! setC.contains(b_item)) return false;
        }

        List<Integer> idxList = new ArrayList<>();
        int prev_idx = 0, length = 0;
        for (int i = 0; i < a.length; i++) {
            if ( ! setC.contains(a[i]) ) {
               idxList.add(i);  // save elements are not in "c"
               if (i - prev_idx - 1 > length) {
                   length = i - prev_idx - 1;
               }
               prev_idx = i;
            }
        }
        if (a.length - prev_idx - 1 > length) {
            length = a.length - prev_idx - 1;
        }
        if (length != b.length) return false;

        String a_str = intArrayToStr(a);
        String b_str = intArrayToStr(b);
        return a_str.contains(b_str);
    }
    public static void main(String[] args) {
//        int[] a = {1,1,2,2,5,1,2}; // false, longest b = [1,1,2,2]
        int[] a = {1,1,5,1,2}; // true,
//        int[] a = {1,1,5,1,2,2,1}; // false, longest b = [1,2,21]
        int[] b = {1,2};
        int[] c = {2,1};
        System.out.println(new longestSubarrayCheck().longestSubarrayCheck(a, b, c));
    }
}
