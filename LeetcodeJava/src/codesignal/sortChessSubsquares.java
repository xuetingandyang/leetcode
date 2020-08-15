package codesignal;

//已有一个白黑格子相间的二维数组,并给一组更换操作的信息,输出操作后的新数组。
// 输入参数(int[][] board, int[][] queries)
// Query 中的信息[x, y, w] -> x, y 表示从数组中坐标为(x, y)的 left-top元素开始操作,操作范围 w * w
//具体操作是在 query 给出范围中,将属于黑格子的按升序排列,将属于白格子的按升序排列

// input: 1,4,3,2  queries = [[0,1,3], [1,0,2]]
//        8,4,7,1
//        1,5,2,1
// [0,1,3] => sort (0,1), 3*3 subsquares => 4,3,2
//                                          4,7,1
//                                          5,2,1
// (0,0)is black, (0,1),(1,0)is white ,...
// ==>   1,1,1,2
//       8,2,4,3
//       1,5,4,7

// [1,0,2] => result =  1,1,1,2
////                    5,1,4,3
////                    2,8,4,7


import java.util.PriorityQueue;

public class sortChessSubsquares {
    public static int[][] sortMatrixByRange(int[][] board, int[][] queries) {

        final int n = board.length;
        final int m = board[0].length;

        for (int[] querie : queries) {
            int x = querie[0];
            int y = querie[1];
            int w = querie[2];
            // in case of 'w' is too large, subsquares is outside of our board
            int top = x;
            int bottom = Math.min(n - 1, x + w - 1);
            int left = y;
            int right = Math.min(m - 1, y + w - 1);

            PriorityQueue<Integer> pqEven = new PriorityQueue<>();
            PriorityQueue<Integer> pqOdd = new PriorityQueue<>();
            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    if ( (i + j) % 2 == 0) {  // Even = black
                        pqEven.add(board[i][j]);
                    } else {  // odd = white
                        pqOdd.add(board[i][j]);
                    }
                }
            }

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    if ( (i + j) % 2 == 0) {  // Even
                        board[i][j] = pqEven.poll();
                    } else {  // odd
                        board[i][j] = pqOdd.poll();
                    }
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        // int[][] orig = {{3,2,1},{6,3,4},{9,5,3}};
        // int[][] queries = {{1,1,1}};
        int[][] orig = {{1,4,3,2},{8,4,7,1},{1,5,2,1}};
        int[][] queries = {{0,1,3}, {1,0,2}};
        int[][] board = sortMatrixByRange(orig, queries);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
