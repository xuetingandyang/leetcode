package codesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    给一个 array 和一个 matrix。
//    matrix 里面每一个 vector<int>的形式必定是[l,r,target]，固定只有 3 个数。
//    然后要求统计 array 里 index 从 l 到 r 这个区间出现了多少次 target 这个数。
//    比如：
//    array = [1,1,2,3,2]
//    matrix = [[1,2,1],
//              [2,4,2],
//              [0,3,1]]
//    output : 5
//    因为在 matrix[0], array 的 index 1 到 2 区间出现了 '1' 一次，
//    matrix[1], array 的 index 2 到 4 区间出现 '2' 两次。
//    matrix[2], array 的 index 0 到 3 区间出现 '1' 两次
//    1 + 2 + 2 = 5
public class CountFreqMatrixQueries {

    public int countFreq(int[] array, int[][] matrix){
        // create a HaspMap (num, [showed indices])
        // (1, [0,1]), (2, [2,4]), (3, [3])
        // count # of map.get(target) that is in [l, r]
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int item = array[i];
            if (! map.containsKey(item)) {
                map.put(item, new ArrayList<>());
            }
            map.get(item).add(i);
        }
        int rst = 0;
        for (int i = 0; i < matrix.length; i++) {
            int l = matrix[i][0], r = matrix[i][1], target = matrix[i][2];
            if (map.containsKey(target)) {
                for (int idx: map.get(target)) {
                    if(idx >= l && idx <= r){
                        rst++;
                    }
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        CountFreqMatrixQueries countFreq = new CountFreqMatrixQueries();
        int[] array = {1,1,2,3,2};
        int[][] matrix = {
                {1,2,1},
                {2,4,2},
                {0,3,1}
        };
        int rst = countFreq.countFreq(array, matrix);
        System.out.println(rst);
    }
}
