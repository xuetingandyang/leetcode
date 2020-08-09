// Given a non-empty array of integers, return the k most frequent elements.
//    Input: nums = [1,1,1,2,2,3], k = 2
//    Output: [1,2]
//
//    Input: nums = [1], k = 1
//    Output: [1]
//  Note:
//    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
//    It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
//    You can return the answer in any order.

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // HashMap: (num, freq)
        // minHeap of size k to keep topK
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(
            k,
            (a, b) -> map.get(a) - map.get(b)
        );
        for (int num : map.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // convert minHeap to array
        // from result[k-1] to result[0]
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i --) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}
