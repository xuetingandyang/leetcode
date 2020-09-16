package amazonOA;

import java.util.Arrays;
import java.util.List;

// (Two Sum Closest) return [idx in arr1, idx in arr2]
// - 实质：给两个array和一个K，每个array里面各选一个数，要求sum<=K的最大组
// - 题目：给一个去程route的list和一个回城route的list，还有一个来回路程的上限max。求来回路程最接近于max的route combination。
// - eg: foreground and background program / forward and return flight 那道题暴力解的
//Input:  ar1[] = {1, 4, 5, 7};
//        ar2[] = {10, 20, 30, 40};
//        x = 32
//        Output:  [0, 2] => 1 and 30
//
//Input:  ar1[] = {1, 4, 5, 7};
//        ar2[] = {10, 20, 30, 40};
//        x = 50
//        Output:  [3, 3] => 7 and 40
public class TwoSumCloestReturnIdx {
    static class Pair {
        int val;
        int idx;
        Pair(){};
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
    public static int[] twoSumClosest(int target, int[] arr1, int[] arr2) {
        // m: arr1 length, n: arr2 length -> Brute Force: O(m*n)
        // sort arr1, arr2 + two pointers -> O(mlogm + nlogn + (m+n))
        // space: O(m + n)
        int m = arr1.length, n = arr2.length;
        Pair[] pair1 = new Pair[m], pair2 = new Pair[n];
        for (int i = 0; i < m; i ++) pair1[i] = new Pair(arr1[i], i);
        for (int i = 0; i < m; i ++) pair2[i] = new Pair(arr2[i], i);

        Arrays.sort(pair1, (a, b) -> a.val - b.val);
        Arrays.sort(pair2, (a, b) -> a.val - b.val);

        int i = 0, j = n - 1, minDiff = Integer.MAX_VALUE;
        int[] rst = new int[2];
        while (i < m && j >= 0) {
            int diff = target - pair1[i].val - pair2[j].val;
            if ( diff >= 0 && diff < minDiff) {
                minDiff = diff;
                rst[0] = pair1[i].idx;
                rst[1] = pair2[j].idx;
            }
            if (diff > 0) { //sum < target, increase sum
                i ++;
            } else if (diff < 0) { // sum > target, decrease sum
                j --;
            } else {
                return rst;
            }
        }
        return rst;
    }
    public static void main(String args[]) {
        int ar1[] = {1, 4, 5, 7};
        int ar2[] = {10, 20, 30, 40};
        int target = 40; // 7, 30 => [3, 2]
        System.out.println(Arrays.toString(twoSumClosest(target, ar1, ar2)));;
    }
}
