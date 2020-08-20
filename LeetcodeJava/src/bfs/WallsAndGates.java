package bfs;

//286. Walls and Gates
//You are given a m x n 2D grid initialized with these three possible values.
//   -1 -   A wall or an obstacle.
//    0 -   A gate.
//   INF -  Infinity means an empty room.
//   We use the value 231 - 1 = 2147483647 to represent INF
//   as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate.
// If it is impossible to reach a gate, it should be filled with INF.

//Given the 2D grid:
//    INF  -1  0  INF
//    INF INF INF  -1
//    INF  -1 INF  -1
//    0  -1 INF INF
//After running your function, the 2D grid should be:
//    3  -1   0   1
//    2   2   1  -1
//    1  -1   2  -1
//    0  -1   3   4

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    private void traverse(int[][] rooms, Queue<int[]> queue) {

        int levelNum = 1;
        while (! queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k ++) {
                int[] pos = queue.poll();
                for (int[] dir : directions) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];

                    if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE) {
                        // update (x,y) as min(prev_levelNum. curt_levelNum)
                        rooms[x][y] = Math.min(rooms[x][y], levelNum);
                        queue.add(new int[]{x, y});
                    }
                }
            }
            levelNum ++;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        // start BFS from all 'gates'
        // level-traverse, turn 'INF' to 'curLevel'
        // (BFS: ensure shorest path,
        // since we search all nodes with dist-'d' before searching nodes with dist-'d+1')

        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // start BFS from gates(0)
        traverse(rooms, queue);
    }
}
