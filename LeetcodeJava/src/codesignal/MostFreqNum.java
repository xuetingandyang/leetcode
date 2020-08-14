package codesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFreqNum {

    public int[] findMostFreqNum(int[] nums){
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int maxFreq = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            while (num != 0) {
                int cur = num % 10;
                map.put(cur, map.getOrDefault(cur, 0) + 1);
                num = num / 10;
                maxFreq = Math.max(maxFreq, map.get(cur));
            }
        }
        List<Integer> list = new ArrayList<>();
        for(Integer i: map.keySet()){
            if(map.get(i) == maxFreq){
                list.add(i);
            }
        }
        int[] rst = list.stream().mapToInt(i -> i).toArray();
        return rst;
    }

    public static void main(String[] args) {
        int[] A = {22,3,33,2,52,55551};
        MostFreqNum mostFreqNum = new MostFreqNum();
        int[] rst = mostFreqNum.findMostFreqNum(A);
        System.out.println(rst);
    }
}
