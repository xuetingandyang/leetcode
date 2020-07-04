// Given a linked list, determine if it has a cycle in it.
// To represent a cycle in the given linked list,
// we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
// If pos is -1, then there is no cycle in the linked list.
//
// pos is not the input of function, but a parameter of constructing cycle list
//
//    Input: head = [3,2,0,-4] with pos = 1
//    Output: true
//    Explanation: There is a cycle in the linked list, where tail connects to the second node.

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    public boolean hasCycleHashTable(ListNode head) {
        // a hash table to record the references of visited nodes
        Set<ListNode> visited = new HashSet<>();
        ListNode curt = head;

        while (curt != null) {
            // meet alredy visited node -> cycle exists
            if (visited.contains(curt)) {
                return true;
            } else {
                visited.add(curt);
            }

            // move next step
            curt = curt.next;
        }
        return false;
    }

    // this method ends with NO extra space -> O(1)
    // Fast pointer moves 2 steps at a time
    // Slow pointer moves 1 step at a time
    // NO cycle: fast pointer reaches end (= null)
    // Yes cycle: fast pointer meets slow pointer
    public boolean hasCycleTwoPointers(ListNode head) {

        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        // while not reach ends
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(-4);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        LinkedListCycle Solution = new LinkedListCycle();
        boolean rst = Solution.hasCycleHashTable(n0);
        boolean rst1 = Solution.hasCycleTwoPointers(n0);
        System.out.println(rst);
    }
}
