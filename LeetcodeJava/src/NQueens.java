// The n-queens puzzle is the problem of placing n queens on an n×n chessboard
// such that no two queens attack each other.
// Given an integer n, return all distinct solutions to the n-queens puzzle.
// Each solution contains a distinct board configuration of the n-queens' placement,
// where 'Q' and '.' both indicate a queen and an empty space respectively.
//
//    Input: 4
//    Output: [
//    [".Q..",  // Solution 1
//    "...Q",
//    "Q...",
//    "..Q."],
//
//    ["..Q.",  // Solution 2
//    "Q...",
//    "...Q",
//    ".Q.."]
//    ]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        /* Queen: kill ALL in same row, same column, same diagonal lines
        * Each row should only have ONE queen.
        * so possible solutions are permutationjs [2, 3, 1, 4], [1, 2, 3, 4], ... etc
        *
        * This problem is find ALL legal permutations -> DFS
        * legal -> not in same row, same column, same diagonals of previous queens
        * */
        List<List<String>> rst = new ArrayList<>();
        if (n == 0) return rst;

        search(rst, new ArrayList<>(), n);

        return rst;
    }

    // rst stores all the lines of the chessboard
    // cols stores the possible column indices for each row
    // 1-recursion definition
    public void search (List<List<String>> rst,
                        List<Integer> cols,
                        int n) {
        // 3-recursion exit
        if (cols.size() == n) {
            rst.add(drawChessboard(cols));
        }

        // 2-recursion split
        for (int i = 0; i < n; i ++) {
            if (!isValid(cols, i, cols.size())) continue;
            cols.add(i);
            search(rst, cols, n);
            cols.remove(cols.size() - 1);
        }

    }


    // Given cols (column indices) and n, check whether col-n can exist in cols.
    // Queen kills:
    // 1. same row: already satisfied
    // 2. same column: check whether n == cols[i],
    // 3. same diagonals:
    //  (x, y)'s diagonals: check abs(slope) == 1
    // abs(x's change) = abs(y'change) for each row
    public boolean isValid(List<Integer> cols, int curCol, int curRow) {
        for (int i = 0; i < curRow; i ++) {
            // case 2: same column
            if (curCol == cols.get(i)) return false;

            // case 3: same diagonals
            // row's absolute change = column's absolute change
            if (Math.abs(cols.get(i) - curCol) == Math.abs(i - cols.size())) return false;
        }
        return true;
    }

    // Given queens location at cols, output chessboard
    // EG: Given [0, 3, 1, 2] Return ["Q...", ".s..Q", ".Q..", "..Q."]
    public List<String> drawChessboard(List<Integer> cols) {
        List<String> board = new ArrayList<>();
        int n = cols.size();
        for (int i = 0; i < n; i ++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j ++) {
                sb.append(cols.get(i) == j? 'Q': '.');
            }
            board.add(sb.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        int n = 4;
        NQueens Solution = new NQueens();
        System.out.println(Solution.solveNQueens(n));
    }
}
