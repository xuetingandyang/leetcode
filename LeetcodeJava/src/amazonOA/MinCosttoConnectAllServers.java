package amazonOA;

// Given an undirected graph with n nodes labeled 1..n.
// Some of the nodes are already connected.
// The i-th edge connects nodes edges[i][0] and edges[i][1] together.
// Your task is to augment this set of edges with additional edges to connect all the nodes.
// Find the minimum cost to add new edges between the nodes such that all the nodes are accessible from each other.
//    Input:
//    n, an int representing the total number of nodes.
//    edges, a list of integer pair representing the nodes already connected by an edge.
//    newEdges, a list where each element is a triplet representing the pair of nodes
//              between which an edge can be added and the cost of addition, respectively
//      (e.g. [1, 2, 5] means to add an edge between node 1 and 2, the cost would be 5).
//    Example 1:
//    Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
//    Output: 7
//    Explanation:
//    There are 3 connected components [1, 4, 5], [2, 3] and [6].
//    We can connect these components into a single component by
//    connecting node 1 to node 2 and node 1 to node 6 at a minimum cost of 5 + 2 = 7.


import java.util.*;

public class MinCosttoConnectAllServers {
    /* Method 1: BFS */
    private Map<Integer, List<int[]>> buildGraph(int n, List<int[]> edges, List<int[]> newEdges) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int i = 1; i <= n; i ++) graph.put(i, new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], 0});
            graph.get(edge[1]).add(new int[]{edge[0], 0});
        }

        for (int[] newEdge : newEdges) {
            graph.get(newEdge[0]).add(new int[]{newEdge[1], newEdge[2]});
            graph.get(newEdge[1]).add(new int[]{newEdge[0], newEdge[2]});
        }
        return graph;
    }

    public int minCost(int n, List<int[]> edges, List<int[]> newEdges) {
        // BFS to find shortest path in simple graph
        // add edge in weight ASC order
        // init original edges with 0-weight

        if (n == 0) return 0;
        Map<Integer, List<int[]>> graph = buildGraph(n, edges, newEdges);
        // sort by weight
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        Set<Integer> seen = new HashSet<>();

        int cost = 0, visitedNum = 0;

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            if (!seen.contains(u[0])) {
                seen.add(u[0]);
                cost += u[1];
                visitedNum ++;
            }
            for (int[] v : graph.get(u[0])) {
                if (!seen.contains(v[0])) pq.add(new int[]{v[0], v[1]});
            }
        }
        return visitedNum == n ? cost : -1;
    }

    /* method 2: Union Find */
    private int[] parents;
    private int visitedNum;

    private int find(int x) {
        // find x's parent
        if (parents[x] == x) return x;
        parents[x] = find(parents[x]); // path compression
        return parents[x];
    }

    private void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX != parentY) {
            parents[parentX] = parentY;
            visitedNum ++;
        }
    }

    public int minCostUnionFind(int n, List<int[]> edges, List<int[]> newEdges) {
        if (n == 0) return 0;
        int cost = 0;
        visitedNum = 0;
        Collections.sort(newEdges, (a, b) -> a[2] - b[2]);

        // init
        parents = new int[n + 1];
        for (int i = 0; i <= n; i ++) parents[i] = i;
        for (int[] edge : edges) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
            }
        }
        for (int[] edge : newEdges) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                cost += edge[2];
            }
        }
        // should left 1 node not visited (edge Number = n - 1)
        return visitedNum == n - 1 ? cost : -1;
    }

    public static void main(String[] args) {
        int n = 6;
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{1,4});
        edges.add(new int[]{4,5});
        edges.add(new int[]{2,3});
        List<int[]> newEdges = new ArrayList<>();
        newEdges.add(new int[]{1,2,5});
        newEdges.add(new int[]{1,3,10});
        newEdges.add(new int[]{1,6,2});
        newEdges.add(new int[]{5,6,5});

        System.out.println(new MinCosttoConnectAllServers().minCost(n, edges, newEdges));
        System.out.println(new MinCosttoConnectAllServers().minCostUnionFind(n, edges, newEdges));
    }
}
