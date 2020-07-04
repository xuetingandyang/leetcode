// Write a program to find the node at which the intersection of two singly linked lists begins.
//    Input:
//      intersectVal = 8,
//      listA = [4,1,8,4,5],
//      listB = [5,6,1,8,4,5],
//      skipA = 2, skipB = 3
//    Output: Reference of the node with value = 8
//
//    Input Explanation:
//    The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
//    From the head of A, it reads as [4,1,8,4,5].
//    From the head of B, it reads as [5,6,1,8,4,5].
//    There are 2 nodes before the intersected node in A;
//    There are 3 nodes before the intersected node in B.


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionofTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // a hash table to record visited nodes in ListA, and see if ListB has same node.
        // time: O(m+n), space: O(m) or O(n)
        Set<ListNode> visited = new HashSet<>();

        ListNode nodeA = headA;
        while (nodeA != null) {
            if (! visited.contains(nodeA)) {
                visited.add(nodeA);
                nodeA = nodeA.next;
            }
        }

        ListNode nodeB = headB;
        while (nodeB != null) {
            if (visited.contains(nodeB)) {
                return nodeB;
            }
            nodeB = nodeB.next;
        }
        return null;
    }

    // Two Pointers, p1 starts from headA, p2 starts from headB, speed are 1 node/move
    // A -- B -- D
    //      |
    // C ---
    // 1. once p1 reaches end, set p1 = headB
    // 2. once p2 reaches end, set p2 = headA
    // 3. when p1 meets p2 -> intersection node
    // Prove:
    //  AB + BD + CB = CB + BD + AB

    public ListNode getIntersectionNodeNoExtraSpace(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
//            // Wrong: cause in one loop, we can only update p1, p2 once! not twice!
//            // listA = [1,2,3] listB = [], then p1 = 1, p2 = null -> p2 = 1 (wrong!!)
//            if (p1 == null) p1 = headB;
//            if (p2 == null) p2 = headA;
//            p1 = p1.next;
//            p2 = p2.next;

            // switch p1, p2 to headB, headA when reaches end
             p1 = (p1 == null) ? headB : p1.next;
             p2 = (p2 == null) ? headA : p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(4);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(8);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        ListNode m0 = new ListNode(5);
        ListNode m1 = new ListNode(6);
        ListNode m2 = new ListNode(1);

        m0.next = m1;
        m1.next = m2;
        m2.next = n2;

        IntersectionofTwoLinkedLists Solution = new IntersectionofTwoLinkedLists();
        ListNode start = Solution.getIntersectionNode(n0, m0);
        ListNode start1 = Solution.getIntersectionNodeNoExtraSpace(n0, m0);
        System.out.println(start.next.val);

    }
}
