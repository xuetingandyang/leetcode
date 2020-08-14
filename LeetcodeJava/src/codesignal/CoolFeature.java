package codesignal;
// Input:
//   a = [1, 2, 3]
//   b = [3, 4]
//   query = [[1, 5], [1, 1, 1], [1, 5]]
// Output: [2, 1]
// Just ignore every first element in sub array in query.
//    So we will get a new query like this query = [[5], [1, 1], [5]]
//    Only record the result when meet the single number in new query array.
//    And the rule of record is find the sum of the single number.
//    The example above is 5 = 1 + 4 and 5 = 2 + 3, there are two result.
//    So currently the output is [2]
//  When we meet the array length is larger than 1, such as [1, 1].
//    That means we will replace the b[x] = y, x is the first element, y is second element.
//    So in this example, the b will be modify like this b = [3, 1]
//    And finally, we meet the [5] again. So we will find sum again. This time the result is 5 = 1 + 4.
//    So currently the output is [2, 1]
//    note: Don't have to modify the query array, just ignore the first element.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoolFeature {

//    private int countTarget(int[] a, int[] b, int target){
//        int rst = 0;
//        for(int A: a){
//            for(int B: b){
//                if(A + B == target){
//                    rst ++;
//                }
//            }
//        }
//        return rst;
//    }

    public List<Integer> findFeature(int[] a, int[] b, int[][] query){
        List<Integer> rst = new ArrayList<>();
        // since only 'b' will be modified, we can record 'a' in a Hashmap(A, freq),
        // and search for 'sum-A' in hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int A : a) {
            map.put(A, map.getOrDefault(A, 0) + 1);
        }

        for (int[] list: query){
            if (list.length == 2) { // find sum = target (list[1])
                int sum = list[1], count = 0;
//                int count = countTarget(a, b, sum);
                for (int B : b) {
                    int target = sum - B;
                    if ( ! map.containsKey(target) ) continue;
                    count += map.get(target);
                }
                rst.add(count);
            }
            else if (list.length == 3) {  // change items
                b[list[1]] = list[2];
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int [] a = {1, 2, 3}, b = {3, 4};
        int[][] query = {{1, 5}, {1, 0, 2}, {1, 5}};
        CoolFeature coolFeature = new CoolFeature();
        List<Integer> rst = coolFeature.findFeature(a, b, query);
        System.out.println(rst);
    }
}
