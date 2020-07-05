// Merge two sorted linked lists and return it as a new sorted list.
// The new list should be made by splicing together the nodes of the first two lists.
//
//    Input: 1->2->4, 1->3->4
//    Output: 1->1->2->3->4->4

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curt = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curt.next = l1;
                l1 = l1.next;
            } else {
                curt.next = l2;
                l2 = l2.next;
            }
            // move next step
            curt = curt.next;
        }

        // if l1 has left nodes, directly connect to curt
        if (l1 != null) curt.next = l1;
        if (l2 != null) curt.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);

        n0.next = n1;
        n1.next = n2;
        n2.next = null;

        ListNode m0 = new ListNode(1);
        ListNode m1 = new ListNode(3);
        ListNode m2 = new ListNode(4);

        m0.next = m1;
        m1.next = m2;
        m2.next = null;

        MergeTwoSortedLists Solution = new MergeTwoSortedLists();
        ListNode sorted = Solution.mergeTwoLists(n0, m0);
        System.out.println(sorted.val);


    }
}
