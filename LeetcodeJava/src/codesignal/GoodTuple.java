package codesignal;

import java.util.HashMap;
import java.util.Map;

//Given a list of integers, count the number of 'good tuples' that can be created.
// A 'good tuple' is defined as consecutive triplets having exactly 2 duplicate elements.
//Target array is a[i - 1], a, a[i + 1]
//        Input: a = [1, 1, 2, 1, 5, 3, 2, 3]
//        Output: 3
//        Explain:
//        [1, 1, 2] -> two 1 and one 2(O)
//        [1, 2, 1] -> two 1 and one 2(O)
//        [2, 1, 5] -> one 2, one 1 and one five(X) [1, 5, 3] -> (X)
//        [5, 3, 2] -> (X)
//        [3, 2, 3] -> (O)

public class GoodTuple {

    public int findGoodTuples(int[] nums){
        int rst = 0;
        for(int i = 1; i < nums.length - 1; i++){
            if( (nums[i] == nums[i - 1] && nums[i] != nums[i + 1]) ||
                (nums[i] == nums[i + 1] && nums[i] != nums[i - 1]) ||
                (nums[i - 1] == nums[i + 1] && nums[i - 1] != nums[i]) ){
                rst++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {4,6,4,4,4,4,2};
        GoodTuple goodTuple = new GoodTuple();
        int rst = goodTuple.findGoodTuples(nums);
        System.out.println(rst);
    }
}
