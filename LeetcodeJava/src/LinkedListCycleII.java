// Given a linked list, return the node where the cycle begins.
// If there is no cycle, return null.
//    To represent a cycle in the given linked list,
//    we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
//    If pos is -1, then there is no cycle in the linked list.
//
//    Note: Do not modify the linked list.
//    Input: head = [3,2,0,-4], pos = 1
//    Output: tail connects to node index 1
//    Explanation: There is a cycle in the linked list, where tail connects to the second node.

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        // use hash table to record visited nodes,
        // the first re-visited node is entry node
        Set<ListNode> visited = new HashSet<>();
        ListNode curt = head;

        while (curt != null) {
            if (! visited.contains(curt)) {
                visited.add(curt);
            } else {
                return curt;
            }
            curt = curt.next;
        }
        return null;
    }

    /* Two Pointers
    * A fast pointer moves 2 steps at a time
    * A slow pointer moves 1 step at a time
    *
    * NO CYCLE: fast pointer goes to end (= null)
    * YES CYCLE:
    *          n6-----------n5
    *           |            |
    *     n1--- n2---n3---- n4
    *
    * Phase1: fast and slow meet at n5.
    *   Suppose distances:
    *       (n1, n2) = a, (n2, n5) = b, (n5, n2) = c
    *   then fast to n5: a + b + c + b
    *        slow to n5: a + b
    *   since they meet, the distFast = 2 * distSlow
    *   a+b+c+b = 2(a+b) => c = a
    *
    * Phase2: move fast to start node (n1), slow stays at meet node
    *  fast (1 step/move), slow (1 step/move)
    *  -> meet at n2 (start cycle node)
    * */
    public ListNode detectCycleNoExtraCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // Phase 1: find first meet node
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // find first meet node, jump out of loop
            if (fast == slow) {
                ListNode firstMeet = fast;
                break;
            }
        }

        // if fast reaches end, there is no cycle
        if (fast == null || fast.next == null) return null;

        // Phase 2: move fast pointer to origin and speed is 1 node/move
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
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

        LinkedListCycleII Solution = new LinkedListCycleII();
        ListNode start = Solution.detectCycle(n0);
        ListNode start1 = Solution.detectCycleNoExtraCycle(n0);
        System.out.println(start.next.val);
    }

}
