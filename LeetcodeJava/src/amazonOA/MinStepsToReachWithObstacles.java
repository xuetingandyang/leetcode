package amazonOA;

// Robot removes obstacles
// '9' is obstacle, '1' flat lot can move, '0' trenches can't move.
// Find the minimum val to reach obstacle in order to remove it
// e.g.
//[1,1,1]
//[1,0,0]
//[1,9,1]
//Output: 3 = 1+1+1

import java.util.LinkedList;
import java.util.Queue;

public class MinStepsToReachWithObstacles {
    public int minSteps(int[][] matrix) {
        // BFS: shortest path in simple graph, when reach '9' break and return
        int m = matrix.length, n = matrix[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        int minPathSum = 0; // actually = number of levels (previous than '9')
        // level-order BFS
        while (! queue.isEmpty()) {
            int size = queue.size();
            minPathSum++;
            for (int i = 0; i < size; i ++) {
                int[] pair = queue.poll();
                int row = pair[0], col = pair[1];
                seen[row][col] = true;

                for (int[] dir : dirs) {
                    int x = pair[0] + dir[0];
                    int y = pair[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && ! seen[x][y]) {
                        if (matrix[x][y] == 9) return minPathSum;
                        if (matrix[x][y] == 1) {
                            seen[x][y] = true;
                            queue.add(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,0},{1,9,1}};
        MinStepsToReachWithObstacles Q = new MinStepsToReachWithObstacles();
        System.out.println("Answer: 3 Actual: " + Q.minSteps(matrix));

        matrix = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {0, 1, 9, 1}};
        System.out.println("Answer: 6 Actual: " + Q.minSteps(matrix));
    }
}
