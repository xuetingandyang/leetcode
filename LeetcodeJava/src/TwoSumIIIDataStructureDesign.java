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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/* The usage pattern: more add(), less find()
 * So we should minimize time cost of add()
 * ==> sort in find()
 * ==> sort in demand (only when nums is updated)
 *      ==> set isSorted to avoid repeating sorting in find()
 * */
public class TwoSumIIIDataStructureDesign {
    private List<Integer> nums;
    private boolean isSorted;

    // Initialize your data structure here
    public TwoSumIIIDataStructureDesign() {
        this.nums = new ArrayList<>();
        this.isSorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
        this.isSorted = false;
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (! isSorted) {
            Collections.sort(this.nums);
            isSorted = true;
        }

        int i = 0, j = this.nums.size() - 1;
        while (i < j) {
            int sum = this.nums.get(i) + this.nums.get(j);
            if (sum == value) {
                return true;
            } else if (sum < value) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        TwoSumIIIDataStructureDesign obj = new TwoSumIIIDataStructureDesign();
        obj.add(3);
        obj.add(1);
        obj.add(2);

        boolean rst = obj.find(5);
        boolean rst1 = obj.find(6);
        System.out.println(rst);
    }
}



