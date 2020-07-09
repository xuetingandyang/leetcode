// Design and implement a TwoSum class.
// It should support the following operations: add and find.
//    add - Add the number to an internal data structure.
//    find - Find if there exists any pair of numbers which sum is equal to the value.

//    Example 1:
//      add(1); add(3); add(5);
//      find(4) -> true
//      find(7) -> false

//    Example 2:
//    add(3); add(1); add(2);
//    find(3) -> true
//    find(6) -> false


import java.util.*;


public class TwoSumIIIDataStructureHashMap {
    /* Hash table: store nums,
    * for each element, check if (target - nums[i]) already exist
    * */
    private List<Integer> nums;
    private Map<Integer, Integer> map;

    // Initialize your data structure here
    public TwoSumIIIDataStructureHashMap() {
        this.nums = new ArrayList<>();
        this.map = new HashMap<>();
    }

    /** Add the number to an internal data structure */
    public void add(int number) {
        this.nums.add(number);
        // set default count=0
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value.
     * find (value - nums.get(i)),
     * if (value - nums.get(i)) == current nums[i],
     *    then check if we have > 1 (value - nums.get(i)) in hashMap*/
    public boolean find(int value) {
        for (int i = 0; i < nums.size(); i ++) {
            int newTarget = value - nums.get(i);
            if (map.containsKey(newTarget)) {
                if ( newTarget == nums.get(i)) {
                    if (map.get(nums.get(i)) > 1) return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        TwoSumIIIDataStructureHashMap obj = new TwoSumIIIDataStructureHashMap();
        obj.add(0);
//        obj.add(1);
//        obj.add(2);
//        obj.add(3);

        boolean rst = obj.find(0);
        boolean rst1 = obj.find(6);
        System.out.println(rst);
    }
}



