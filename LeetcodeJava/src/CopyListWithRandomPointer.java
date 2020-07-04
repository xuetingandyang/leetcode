// A linked list is given such that each node contains
// an additional random pointer which could point to any node in the list or null.
//  Return a deep copy of the list.
//
//    The Linked List is represented in the input/output as a list of n nodes.
//    Each node is represented as a pair of [val, random_index] where:
//
//    val: an integer representing Node.val
//    random_index: the index of the node (range from 0 to n-1)
//                  where random pointer points to, or null if it does not point to any node.

//        Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//        Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]


import java.util.HashMap;
import java.util.Map;

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}


public class CopyListWithRandomPointer {

    // key, value ===> oldNode, newNode
    Map<Node, Node> visited = new HashMap<>();


    private Node cloneNode(Node node) {
        if (node == null) return null;

        // if node has been visited, return the newNode's reference (through get(node))
        // if not visited yet, deep copy a newNode
        if (!visited.containsKey(node)) {
            visited.put(node, new Node(node.val));
        }
        return visited.get(node);
    }

    public Node copyRandomListHashmap(Node head) {
        if (head == null) return null;


        Node oldNode = head;
        // deep copy: create newNode.val = head.val
        Node newNode = new Node(oldNode.val);
        // put it in visited hashmap to avoid repeats in cycle
        visited.put(oldNode, newNode);

        // Iterate over linkedlist until all nodes are cloned
        while (oldNode != null) {
            newNode.next = cloneNode(oldNode.next);
            newNode.random = cloneNode(oldNode.random);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return visited.get(head);
    }

    public Node copyRandomListNoExtraSpace(Node head) {
        if (head == null) return null;

        // create interwaving list of oldNode and newNode
        Node oldNode = head;
        while (oldNode != null) {
            // oldNode1 -> newNode1 -> oldNode2 -> newNode2 ->...
            Node newNode = new Node(oldNode.val);
            newNode.next = oldNode.next;
            oldNode.next = newNode;
            // move next step
            oldNode = newNode.next;
        }

        // assign references of random pointers for newNodes
        // oldNode1.random = oldNode4 -> newNode1.random = newNode4
        // ===> oldNode1.next.random = oldNode1.random.next
        oldNode = head; // start from head node
        while (oldNode != null) {
            // NOTE!! avoid error, ensure oldNode.random is not null
            oldNode.next.random = (oldNode.random != null) ? oldNode.random.next : null;
            // move next step
            oldNode = oldNode.next.next;
        }

        // split newNodes from interwaving list
        // oldNode1 -> newNode1 -> oldNode2 -> newNode2 -> ...
        // oldNode1.next = oleNode1.next.next
        // newNode1.next = newNode1.next.next
        oldNode = head; // start from head node
        Node newNode = head.next;
        Node newHead = newNode; // keep newList's head node for return
        while (oldNode != null) {
            // Ensure oldNode.next, newNode.next != null
            oldNode.next = (oldNode.next != null) ? oldNode.next.next : null;
            newNode.next = (newNode.next != null) ? newNode.next.next : null;
            // move next setp
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return newHead;

    }

    public static void main(String[] args) {
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n0.random = null;
        n1.random = n0;
        n2.random = n4;
        n3.random = n2;
        n4.random = n0;

        CopyListWithRandomPointer Solution = new CopyListWithRandomPointer();
//        Node deepCopy = Solution.copyRandomListHashmap(n0);
        Node deepCopy = Solution.copyRandomListNoExtraSpace(n0);
        System.out.println(deepCopy.next.val);

    }
}
