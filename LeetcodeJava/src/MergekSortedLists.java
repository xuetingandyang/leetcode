// Merge k sorted linked lists and return it as one sorted list.
// Analyze and describe its complexity.
//    Input:
//    [
//    1->4->5,
//    1->3->4,
//    2->6
//    ]
//    Output: 1->1->2->3->4->4->5->6

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // BruteForce: get each node into array, then sort array
        // => change array to LinkedList
        // O(NlogN) time, O(N) space
        ListNode head = new ListNode();
        ListNode point = new ListNode();
        head = point;

        ArrayList<Integer> nodes = new ArrayList<>();

        for (ListNode list : lists) {
            while (list != null) {
                nodes.add(list.val);
                list = list.next;
            }
        }
        Collections.sort(nodes);
        for (int num : nodes) {
            point.next = new ListNode(num);
            point = point.next;
        }
        return head.next;
    }

    public ListNode mergeKListsMinHeap(ListNode[] lists) {
        // use PriorityQueue(minHeap) to get min from 'K nodes'
        // for each node, time: O(logK) to add into minHeap of size K
        // Time: O(NlogK), space: O(K) to keep K nodes in minHeap
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        // arrow function simplify the 'comparator function override'
        // remember: (a, b) -> a - b => from low to high
        //           (a, b) -> b - a => from high to low
        ListNode head, point = new ListNode();
        head = point;
        for (ListNode list : lists) {
            if (list != null) {
                // in case of input: [[1,4,5],[],[2,6]]
                minHeap.add(list);
            }
        }
        while (minHeap.size() != 0) {
            // add curMin node to result
            // remove curMin from minHeap
            // move point to next one
            ListNode curMin = minHeap.poll();
            point.next = curMin;
            point = point.next;

            // add newMin = curMin.next to minHeap
            ListNode newMin = curMin.next;
            if (newMin != null) {
                minHeap.add(newMin);
            }
        }
        return head.next;
    }
}


