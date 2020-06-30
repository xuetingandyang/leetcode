// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//    k is a positive integer and is less than or equal to the length of the linked list.
//    If the number of nodes is not a multiple of k
//    then left-out nodes in the end should remain as it is.

//    Given this linked list: 1->2->3->4->5
//    For k = 2, you should return: 2->1->4->3->5
//    For k = 3, you should return: 3->2->1->4->5

//    Only constant extra memory is allowed.
//    You may not alter the values in the list's nodes,
//    only nodes itself may be changed.

public class ReverseNodesKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0, head); // dummy node
        // D->[3->2->1]->4->5->6->7
        // D->[3->2->1]->[6->5->4]->7

        ListNode prev = dummy;

        while (prev != null) {
            prev = reverseKNodes(prev, k);
        }

        return dummy.next;
    }

    // head -> n1 -> n2 -> .. -> nk -> nk+1
    // ===>
    // head -> nk -> nk-1 -> .. -> n1 -> nk+1
    // return the dummy head node of K groups
    private ListNode reverseKNodes(ListNode head, int k) {
        ListNode curt = head;
        ListNode n1 = head.next;

        // Find nk node
        for (int i = 0; i < k; i ++) {
            curt = curt.next;

            // not enough for k, return null
            if (curt == null) return null;
        }
        // Find nk and nk+1 node
        ListNode nk = curt;
        ListNode nkplus = curt.next;

        // Find all nodes that needs reverse, reverse it!
        // reverse Linked List
        ListNode prev = head;
        curt = head.next;
        while (curt != nkplus) {
            ListNode nextTemp = curt.next;
            curt.next = prev;

            // update curt and prev nodes
            prev = curt;
            curt = nextTemp;
        }

        // link head -> nk
        head.next = nk;
        // link n1 -> nk+1
        n1.next = nkplus;

        // return dummy head node for next K groups
        return n1;

    }

    public static void main(String[] args) {
        ReverseNodesKGroup Solution = new ReverseNodesKGroup();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        ListNode reversed = Solution.reverseKGroup(l1, 3);
        System.out.println(reversed.next.val);
    }
}
