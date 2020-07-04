// Sort a linked list in O(n log n) time
// using constant space complexity.
//    Input: 4->2->1->3
//    Output: 1->2->3->4

//    Input: -1->5->3->4->0
//    Output: -1->0->3->4->5

import java.util.List;

public class SortLinkedList {

    private ListNode merge(ListNode l1, ListNode l2) {
        // LinkedList's structure will change, the head node will change, too
        // We use dummy node to point to head. (Dummy -> list...)
        ListNode dummy = new ListNode(0);
        ListNode curt = dummy;  // modify curt.next to change the LinkedList's structure

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curt.next = l1; // curt.next is the real head of LinkedList
                l1 = l1.next;
            } else {
                curt.next = l2;
                l2 = l2.next;
            }
            // move to next node
            curt = curt.next;
        }

        // if l1 is shorter, we finish traversing l1 first
        if (l1 == null) {
            curt.next = l2; // the left are l2
        }
        if (l2 == null) {
            curt.next = l1;
        }

        // 'curt' reaches end, but dummy -> sorted -> 'curt'
        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        // merge sort
        // merge 2 sorted lists in-place

        if (head == null || head.next == null) return head;

        // 1-cut into 2 halves, find middle node
        // Two pointers, fast: 2 nodes/move, slow: 1 node/move
        // prev: previous node of slow pointer, used to cut into halves
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            // move next step
            slow = slow.next;
            fast = fast.next.next;
        }

        // cut: .. -> prev -> slow (mid node) -> ..
        // set prev's next = null
        prev.next = null;

        // 2-sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // 3-merge l1 and l2
        return merge(l1, l2);
    }


    public static void main(String[] args) {
        ListNode n0 = new ListNode(4);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(3);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = null;

        SortLinkedList Solution = new SortLinkedList();
        ListNode sorted = Solution.sortList(n0);
        System.out.println(sorted.val);
    }
}
