package codesignal;

//从数组中找出任意两个数字，可重复，使得他们的和是 power of 2，
// 求所有可能的次数
//    note： 暴力会fail 隐藏的test case
//    input: a = [1, -1, 2, 3]
//    expected output: 5
//    Explanation:
//    a[1] + a[2] = 1
//    a[0] + a[0] = 2
//    a[1] + a[3] = 2
//    a[0] + a[3] = 4
//    a[2] + a[2] = 4

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class PairSumToPowerOfTwo {
    public int pairSummingToPowerOfTwo(int[] a){
        int n = a.length;
        // To keep the element in sorted order
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++)
        {
            map.put(a[i], 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int item = entry.getKey();
            if(item < 1){
                break;
            }
            int cur = 1;
            while(cur <= item * 2){
                if(map.containsKey(cur - item)){
                    count ++;
                }
                cur *= 2;
            }
        }
        return count;
    }



    public static void main(String[] args) {
        int[] a = {1, -1, 2, 3};
        System.out.println(new PairSumToPowerOfTwo().pairSummingToPowerOfTwo(a));
    }

}
