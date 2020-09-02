package amazonOA;

import java.util.Arrays;
import java.util.List;

public class SubtreeWithMaxAverage {
    static class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int val) { this.val = val; }
        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    double maxAvg = Integer.MIN_VALUE;
    Node maxNode = new Node();

    private double[] helper(Node node) {
        // recursion exit (leaf node and null node)
        if (node == null) return new double[]{0, 0};
        if (node.children == null) return new double[]{node.val, 1};

        double sum = node.val, count = 1;
        // recursion split
        for (Node child : node.children) {
            double[] curt = helper(child);
            sum += curt[0];
            count += curt[1];
        }
        // update maxAvg & maxNode, notice: "at least 1 child"
        double avg = sum / count;
        if (count > 1 && avg > maxAvg) {
            maxAvg = avg;
            maxNode = node;
        }
        return new double[]{sum, count};
    }

    public Node getMaximumAverage(Node root) {
        // DFS: almost all binary tree questions can be solved by DFS
        // root.avg = (l.sum + r.sum) / (l.nodes + r.nodes)
        // so helper() should return int[] = (sum, count)
        if (root == null) return null;
        helper(root);
        return maxNode;
    }


    public static void main(String[] args) {
        // Input:
        //              20
        //            /   \
        //          12     18
        //       /  |  \   / \
        //     11   2   3 15  8
        Node left = new Node(12);
        left.children = Arrays.asList(new Node(11), new Node(2), new Node(3));

        Node right = new Node(18);
        right.children = Arrays.asList(new Node(15), new Node(8));

        Node root = new Node(20);
        root.children = Arrays.asList(left, right);

        test(root); // output: 18
    }

    private static void test(Node root) {
        Node maxNode = new SubtreeWithMaxAverage().getMaximumAverage(root);
        System.out.println("Max Average Node: " + maxNode.val);
    }
}
