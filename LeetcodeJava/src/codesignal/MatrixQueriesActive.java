package codesignal;

//给你一个2d array。其中array​[i][j] = (i+1)*(j+1)。这个给定。
// 然后给一堆query，有三种不同的格式:
// [0] 第一种是让你返回当前array中的最小值
// [1, i] 第二种是让你disable i-row
// [2, j] 第三种是把disable j-column
// ​​​​​​​​​​​​​​​​​​​当然disable了之后最小值就不能用了
// Notice: matrix are 1-based, index start with 1

// input: row: n=3, col: m=4,
//        queries = [[0],[1,2],[0],[2,1],[0],[1,1],[0]]
// matrix = [1,2,3,4]
//          [2,4,6,8]
//          [3,6,9,12]

// [0]=> min = 1
// [1,2] => disable row-2 => min=1
// [2,1] => disable col-1 => min=2
// [1,1] => disable row-1 => min=6
// output = [1,1,2,6]

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MatrixQueriesActive {
    public static List<Integer> findMin(int m, int n, int[][] queries) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            if (query.length == 1) {
                for(int key : map.keySet()) {
                    if(map.get(key) != 0) {
                        res.add(key);
                        break;
                    }
                }
            } else if (query.length == 2) {
                if(query[0] == 1) { // disable row, disabled = -1
                    int row = query[1] - 1;
                    for(int j = 0; j < n; j++) {
                        if (matrix[row][j] != -1 && map.containsKey(matrix[row][j])) {
                            map.put(matrix[row][j], map.get(matrix[row][j]) - 1);
                            matrix[row][j] = -1;
                        }
                    }
                } else {  // disable column
                    int col = query[1] - 1;
                    for(int i = 0; i < m; i++) {
                        if (matrix[i][col] != -1 && map.containsKey(matrix[i][col])) {
                            map.put(matrix[i][col], map.get(matrix[i][col]) - 1);
                            matrix[i][col] = -1;
                        }
                    }
                }
            }}
        return res;
    }

    public static void main(String[] args) {
        int n = 3, m = 4;
        int[][] queries = {{0},{1,2},{0},{2,1},{0},{1,1},{0}};
        List<Integer> result = findMin(n, m, queries);
        System.out.println(result);
    }
}
