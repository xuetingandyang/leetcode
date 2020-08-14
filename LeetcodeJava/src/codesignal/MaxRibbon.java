package codesignal;

import java.util.Arrays;

// or CutWood:
// Given an int array wood representing the length of n pieces of wood and an int k.
// It is required to cut these pieces of wood
// such that more or equal to k pieces of the same length len are cut.
// What is the longest len you can get?
//    Input: wood = [5, 9, 7], k = 4
//    Output: 4
//    Explanation:
//    5 -> 4 + 1
//    9 -> 4 * 2 + 1
//    7 -> 4 + 3
// Ex.
//     Input: A = [1, 2, 3, 4, 9], k = 5
//     Output: 3
//
//     Explanation:
//     if size = 1, [1//1, 2//1, 3//1, 4//1, 9//1] -> sum([1, 2, 3, 4, 9]) = 19 parts
//     if seize‍‍‌‌‍‌‍‌‌‍‍‍‍‍‌‌‍‍‌ = 2, [1//2, 2//2, 3//2, 4//2, 9//2] -> sum([0, 1, 1, 2, 4]) = 8 parts
//     if size = 3, then we have 5 parts
//     if size = 4, then we have 3 parts, which is not enough.
//     So return the max size = 3.
//
//     Sol.
//     Use binary search to find the size of ribbon to reach the time limit.
public class MaxRibbon {

    private boolean isValid(int[] wood, int length, int k){
        int num = 0;
        for(int w: wood){
            num += w / length;
        }
        return num >= k;
    }

    public int maxRibbon(int[] wood, int k){
        int max = Arrays.stream(wood).max().getAsInt();

        int left = 1, right = max;
        int rst = 0;
        while (left <= right) {
            int mid = left + (right  - left ) / 2;
            if (isValid(wood, mid, k)) {
                // satisfy, increase to get maxCutLength
                left = mid + 1;
                rst = mid;
            } else {
                // too big, decrease it to satisfy "at least k"
                right = mid - 1;
            }
        }
        return rst;
    }


    private boolean valid(int[] piles, int len, int H){
        int num = 0;
        for(int pile: piles){
            num += pile / len;
            if (pile % len != 0) {
                num ++;
            }
        }
        return num <= H;
    }

    public int minEatingSpeed(int[] piles, int H) {
//    Given n pieces of wood with length L[i] (integer array).
//    Cut them into small pieces to guarantee you could have
//       at most k pieces with as small cutting length as possible.
//
//    Given L & k, return the max length of the small pieces.
//    (i.e. the min cutting length -> eating speed)
//    Notice: You couldn't cut wood into float length.
//    If you couldn't get <= k pieces, return 0.

//    For L=[3, 6, 7, 11], k=8, return 4.
//    3, 4+2, 4+3, 4+4+3 --> 4
        if(piles == null || piles.length == 0 || H == 0){
            return 0;
        }
        int max = Arrays.stream(piles).max().getAsInt();
        int left = 1, right = max;
        int rst = 0;
        // binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(piles, mid, H)) {
                rst = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        MaxRibbon maxRibbon =  new MaxRibbon();
//        int[] wood = {232, 124, 456};
//        int k = 7; // => 114
        int[] wood = {5,9,7};
        int k = 5; // => 5
        int rst = maxRibbon.maxRibbon(wood, k);

        int[] piles = {3,6,7,11};
        int H = 8;
        int rst2 = maxRibbon.minEatingSpeed(piles, H);
        System.out.println(rst);
        System.out.println(rst2);
    }
}
