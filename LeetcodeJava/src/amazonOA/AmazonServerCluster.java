package amazonOA;

// find how many clusters are in the grid currently.
// Input: int numOfRows, List<String> grid: 2D grid of servers
// Output: int # of clusters present in the grid

// Notes: grid[i][j] consists 'a', 'b', 'c' only
// eg: intput: numOfRows = 3, grid = ["aabba", "aabba", "aaacb"]
// output: 5
// a, a, b, b, a
// a, a, b, b, a
// a, a, a, c, b
// count connected components: 5
// ('a': 2 + 'b' 2 + 'c' 1) = 5

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AmazonServerCluster {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void findCluster(char[][] charGrid, int i, int j, Queue<int[]> queue) {
        // find connected components (same with charGrid[i][j]) and mark them to '0'
        char label = charGrid[i][j];

        while ( ! queue.isEmpty() ) {
            int[] pos = queue.poll();
            for (int[] dir : dirs) {
                int x = pos[0] + dir[0];
                int y = pos[1] + dir[1];
                if (x >= 0 && x < charGrid.length && y >= 0 && y < charGrid[0].length && charGrid[x][y] == label) {
                    charGrid[x][y] = '0';
                    queue.add(new int[]{x, y});
                }
            }
        }
    }

    public int countClusters(int numOfRows, List<String> grid) {

        int numOfCols = grid.get(0).length();
        char[][] charGrid = new char[numOfRows][numOfCols];

        for (int i = 0; i < numOfRows; i ++) {
            charGrid[i] = grid.get(i).toCharArray();
        }
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < numOfRows; i ++) {
            for (int j = 0; j < numOfCols; j ++) {
                if (charGrid[i][j] == 'a' || charGrid[i][j] == 'b' || charGrid[i][j] == 'c') {
                    queue.add(new int[]{i, j});
                    findCluster(charGrid, i, j, queue);
                    count ++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int numOfRows = 3;
        List<String> grid = new ArrayList<>(List.of("aabba", "aabba", "aaacb"));
        System.out.println(new AmazonServerCluster().countClusters(numOfRows, grid));
    }
}
