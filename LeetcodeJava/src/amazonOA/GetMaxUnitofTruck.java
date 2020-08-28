package amazonOA;
// create a shipment to fill a truck.
// all products are in same size boxes
// Given #boxes that the truck can hold
// Task: determine the maxNumber of units of any mix of products that can ship

// Input: int num: # of products
//        List<Integer> boxes: # of available boxes for products
//        int unitSize: size of unitsPerBox
//        List<Integer> unitsPerBox: # of units packed in each box
//        int truckSize: # of boxes that a truck can carry
// Output: int: max units that can be carried by the truck

// num = 3, boxes = [1,2,3],
// unitSize=3, unitsPerBox = [3,2,1], truckSize = 3

// product-0: 1 box with 3 units
//         1: 2          2
//         2: 3          1

// output = Product-0 + 2 * Prodyct-1 = 3 + 2*2 = 7

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GetMaxUnitofTruck {
    // minHeap of size=truckSize, save the #unitsPerBox
    // for loop of [3,2,2,1], add into minHeap
    // return sum of minHeap
    long getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize, ArrayList<Integer> unitsPerBox, long truckSize) {
        long rst = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < num; i ++) {
            int box = boxes.get(i);
            while (box > 0) {
                minHeap.add(unitsPerBox.get(i));
                if (minHeap.size() > truckSize) minHeap.poll();
                box--;
            }
        }

        while (!minHeap.isEmpty()) {
            rst += minHeap.poll();
        }

        return rst;
    }

    public static void main(String[] args) {
        int num = 3, unitSize = 3;
        long truckSize = 4;
        ArrayList<Integer> boxes = new ArrayList<>(List.of(1,2,3,4));
        ArrayList<Integer> unitsPerBox = new ArrayList<>(List.of(3,2,1,5));

        System.out.println(new GetMaxUnitofTruck().getMaxUnit(num, boxes, unitSize, unitsPerBox, truckSize));
    }
}
