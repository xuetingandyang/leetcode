// Find the kth largest element in an unsorted array.
// Note that it is the kth largest element in the sorted order,
// not the kth distinct element.
//
//    Input: [3,2,1,5,6,4] and k = 2
//    Output: 5
//
//    Input: [3,2,3,1,2,4,5,5,6] and k = 4
//    Output: 4
//    Note:
//      You may assume k is always valid, 1 ≤ k ≤ array's length.

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class KthLargestElementInArray {

    public int findKthLargestMinHeap(int[] nums, int k) {
        // O(N lg K) running time + O(K) memory
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public int findKthLargestMaxHeap(int[] nums, int k) {
        // O(N lg K) running time + O(K) memory
        if (nums == null || nums.length == 0 || k == 0) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> (b-a));

        for (int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
        }
        for (int j = 0; j < k - 1; j++) {
            heap.poll();
        }
        return heap.peek();
    }


    public int findKthLargestPriorityQueue(int[] nums, int k) {
        // time: O(N logk) space: O(k)
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> n1 - n2);

        // keep k largest elements iin the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }


    /* Quick select too hard, skip */

    int[] nums;
    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }
    private int partitionArray(int i, int j, int p) {
        // partition array: nums[i : j]
        int pivot = this.nums[p];
        // 1. move pivot to end
        swap(p, j);
        int store_index = i;

        // 2. move all smaller elements to the left
        for (int ii = i; ii <= j; ii++) {
            if (this.nums[ii] < pivot) {
                swap(store_index, ii);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, j);

        return store_index;
    }

    private int quickSelect(int i, int j, int k) {
        // quick select 'n-k' smallest node between [i, j]

        // if nums[i : j] only contains 1 element, return it
        if (i == j) return this.nums[i];

        // pick a random pivot from i to j
        Random random_num = new Random();
        int p = i + random_num.nextInt(j - i);

        // partition array: all left nodes < nums[p], right >= nums[p]
        // return partition index new 'p'
        p = partitionArray(i, j, p);

        if (p == k) {   // the pivot is the (n-k) smallest -> k-largest
            return this.nums[p];
        } else if (p < k) {     // go right
            return quickSelect(p + 1, j, k);
        } else {    // go left
            return quickSelect( i, p - 1, k);
        }
    }

    public int findKthLargestQuickSelect(int[] nums, int k) {
        // Quick Select
        // k-th largest = n- k smallest
        //  1. pick random pivot = 'p'
        //  2. partition array: costs O(n) time
        //        all left < nums[p], all right >= nums[p]
        //     return position 'pos'
        //  3. if pos < 'n - k':
        //        recursively repeat step 1, 2
        //        until pos = 'n - k'
        int n = nums.length;
        this.nums = nums;
        // quick select 'n-k' smallest
        return quickSelect(0, n - 1, n - k);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,5,6,4};
        int k = 2;
        KthLargestElementInArray Solution = new KthLargestElementInArray();
        int num = Solution.findKthLargestPriorityQueue(nums, k);
        int num1 = Solution.findKthLargestMinHeap(nums, k);
        int num2 = Solution.findKthLargestMaxHeap(nums, k);
        int num3 = Solution.findKthLargestQuickSelect(nums, k);
        System.out.println(num);
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
    }
}
