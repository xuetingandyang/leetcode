import java.util.List;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {

        ListNode curt = head;
        ListNode prev = null;

        while (curt != null) {
            // store next node before changing the references
            ListNode nextTemp = curt.next;
            curt.next = prev;

            // update prev, curt nodes
            prev = curt;
            curt = nextTemp;
        }
        return prev;
    }


    public static void main(String[] args) {
        ReverseLinkedList Solution = new ReverseLinkedList();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = null;

        ListNode reversed = Solution.reverseList(l1);
        System.out.println(reversed.next.val);
    }
}