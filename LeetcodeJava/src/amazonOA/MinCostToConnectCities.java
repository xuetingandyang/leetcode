package amazonOA;

// There are N cities numbered from 1 to N.
// You are given connections, where each connections[i] = [city1, city2, cost]
// represents the cost to connect city1 and city2 together.
// (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
// Return the minimum cost so that for every pair of cities,
// there exists a path of connections (possibly of length 1) that connects those two cities together.
// The cost is the sum of the connection costs used.
// If the task is impossible, return -1.
//    Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
//    Output: 6
//    Explanation:
//    Choosing any 2 edges will connect all cities so we choose the minimum 2.
//
//    Input: N = 4, connections = [[1,2,3],[3,4,4]]
//    Output: -1
//    Explanation:
//    There is no way to connect all cities even if all edges are used.


import java.util.*;

public class MinCostToConnectCities {
    /* method1- Union Find */
    // Sort edges to no-descresing order
    // Pick the smallest edge that does not form a cycle
    // Repeat until MST is formed and every node is connected.
    // Implemented Union-Find with path comression to improve efficiency.
    private int edgesNum;
    private int[] parents;

    private int find(int x) {
        // find node's parent
        if (parents[x] == x) return x;
        // path compression
        parents[x] = find(parents[x]);
        return parents[x];
    }

    private void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {
            // set parentX's parent = parentY
            parents[parentX] = parentY;
            edgesNum ++;
        }
    }

    public int minimumCost(int N, int[][] connections) {
        // sort edges by weight in ASC order
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        // init each node's parent = itself
        parents = new int[N + 1];
        edgesNum = 0;
        for (int i = 0; i <= N; i ++) {
            parents[i] = i;
        }

        int cost = 0;

        for (int[] edge : connections) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                cost += edge[2];
            }
        }

        return edgesNum == N-1 ? cost : -1;
    }


    /* method 2: BFS */
    private Map<Integer, List<int[]>> buildGraph(int n, int[][] connections) {
        // (1, [[2,5],[3,6]]), (2,[[1,5],[3,1]]), (3, [[1,6],[2,1]])

        Map<Integer, List<int[]>> graph = new HashMap<>();
        // init graph (node start from 1 not 0)
        for (int i = 0; i < n; i ++) graph.put(i + 1, new ArrayList<>());

        // based on connections init edges
        for (int[] edge : connections) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        return graph;
    }

    public int minimumCostBFS(int n, int[][] connections) {
        // BFS to find shortest path in Graph
        // Queue use PQ with weight ASC order
        // i.e. add small weight first
        if (n == 0) return 0;

        // (nodeU, [nodeV, edgeWeight], [..], [..])
        Map<Integer, List<int[]>> graph = buildGraph(n, connections);

        int visitedNum = 0, cost = 0;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1,0});

        Set<Integer> seen = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            if (seen.contains(u[0])) continue;

            seen.add(u[0]);
            visitedNum ++;
            cost += u[1];

            for (int[] v : graph.get(u[0])) {
                if ( !seen.contains(v[0]) )
                    pq.add(new int[]{v[0], v[1]});
            }
        }

        return visitedNum == n ? cost : -1;
    }
}
