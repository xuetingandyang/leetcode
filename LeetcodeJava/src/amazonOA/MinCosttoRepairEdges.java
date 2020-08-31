package amazonOA;

//    There's an undirected connected graph with n nodes labeled 1..n.
//    But some of the edges has been broken disconnecting the graph.
//    Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
//
//    Input:
//    n, an int representing the total number of nodes.
//    edges, a list of integer pair representing the nodes connected by an edge.
//    edgesToRepair, a list where each element is a triplet representing the pair of nodes
//          between which an edge is currently broken and the cost of repairing that edge, respectively
//          (e.g. [1, 2, 12] means to repair an edge between nodes 1 and 2, the cost would be 12).
//    Example 1:
//    Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
//    Output: 20
//    Explanation:
//    There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
//    We can connect these components into a single component by
//    repairing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.

//    Example 2:
//    Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
//    Output: 410

//    Example 3:
//    Input: n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]],
//    edgesToRepair = [[1, 5, 110], [2, 4, 84], [3, 4, 79]]
//    Output: 79

import java.util.*;

public class MinCosttoRepairEdges {
    private int[] parents;
    private int visitedNum;

    private int find(int x) {
        // find x's parent
        if (parents[x] == x) return x;
        // path compression
        parents[x] = find(parents[x]);
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
    private boolean isBroken(int[] edge, int[][] edgesToRepair){
        for (int i = 0; i < edgesToRepair.length; i ++) {
            if (edge[0] == edgesToRepair[i][0] && edge[1] == edgesToRepair[i][1])
                return true;
        }
        return false;
    }
    public int minCostToPrepair(int n, int[][] edges, int[][] edgesToRepair) {
        // Union Find: edges (0-weight)
        // init
        parents = new int[n + 1];
        int cost = 0;
        for (int i = 1; i <= n; i ++) parents[i] = i;
        // sort repair edges by weighht
        Arrays.sort(edgesToRepair, (a, b) -> a[2] - b[2]);

        for (int[] edge : edges) {
            if (isBroken(edge, edgesToRepair)) continue;
            if (find(edge[0]) != find(edge[1])) union(edge[0], edge[1]);
        }
        for (int[] edge : edgesToRepair) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                cost += edge[2];
            }
        }
        // if only left 1 unvisited node, then return cost, else -1
        return visitedNum == n-1 ? cost : -1;
    }


    /* method2: BFS to find shortest path in simple graph
    * (undirected graph with 0 weight) */

    private Map<Integer, List<int[]>> buildGraph(int n, int[][] edges, int[][] edgesToRepair) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        // init
        for (int i = 1; i <= n; i ++) graph.put(i, new ArrayList<>());

        for (int[] e : edges) {
            if (isBroken(e, edgesToRepair)) continue;
            graph.get(e[0]).add(new int[]{e[1], 0});
            graph.get(e[1]).add(new int[]{e[0], 0});
        }

        for (int[] e : edgesToRepair) {
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }
        return graph;
    }

    public int minCostToPrepairBFS(int n, int[][] edges, int[][] edgesToRepair) {
        Map<Integer, List<int[]>> graph = buildGraph(n, edges, edgesToRepair);

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        // record seen nodes to avoid repeat visit and cycle
        Set<Integer> seen = new HashSet<>();

        int visitedNum = 0, cost = 0;
        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            if (! seen.contains(e[0])) {
                seen.add(e[0]);
                visitedNum ++;
                cost += e[1];
            }
            for (int[] u : graph.get(e[0])) {
                if (! seen.contains(u[0])) {
                    pq.add(new int[]{u[0], u[1]});
                }
            }
        }
        return visitedNum == n ? cost : -1;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,2},{2,3},{3,4},{4,5},{1,5}};
        int[][] edgesToRepair = {{1,2,12}, {3,4,30}, {1,5,8}};
        System.out.println("Answer for Q1: " + new MinCosttoRepairEdges().minCostToPrepair(n, edges, edgesToRepair));
        System.out.println("Answer for Q1: (BFS) " + new MinCosttoRepairEdges().minCostToPrepairBFS(n, edges, edgesToRepair));

        edges = new int[][] {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
        edgesToRepair = new int[][] {{1, 6, 410}, {2, 4, 800}};
        System.out.println("Answer for Q2: " + new MinCosttoRepairEdges().minCostToPrepair(6, edges, edgesToRepair));
        System.out.println("Answer for Q2: (BFS) " + new MinCosttoRepairEdges().minCostToPrepairBFS(6, edges, edgesToRepair));

        edges = new int[][] {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
        edgesToRepair = new int[][] {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
        System.out.println("Answer for Q3: " + new MinCosttoRepairEdges().minCostToPrepair(6, edges, edgesToRepair));
        System.out.println("Answer for Q3: (BFS) " + new MinCosttoRepairEdges().minCostToPrepairBFS(6, edges, edgesToRepair));
    }
}
