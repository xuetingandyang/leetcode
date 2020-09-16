package amazonOA;

//卡车送货的题 (k closest points to origin)
// N个地点List<List<Integer>> 地点的坐标, M代表需要送的数量
// output：一个List<List<Integer>> 代表送货的地点坐标x,y, 其实就是让你计算距离卡车(0,0)最近的M个地点.
// eg. N = 3, M = 2, List<List<Ingeter>> 是 [[2,3][3,4],[1,-3]]  output: [[2,3],[1,-3]]
// dist to (0,0) = [4+9, 9+16, 1+9] = [13, 25, 10] => nearest M: [10,13] =>[2,3],[1,-3]

import java.util.*;

public class KClosestPointsToOrigin {
    public static List<int[]> kClosest(List<int[]> points, int K) {
        // use maxHeap of size K to save top K lowest dist
        // each time newDist will compare to K-th nearest dist (pq.poll())
        // maxHeap: (x, y, dist), ordered by dist DESC
        Queue<int[]> maxHeap = new PriorityQueue<>(K, (a, b) -> b[2] - a[2]);

        for (int[] point : points) {
            int x = point[0], y = point[1];
            int dist = (int)(Math.pow(x, 2) + Math.pow(y, 2));
            maxHeap.add(new int[]{x, y, dist});
            if (maxHeap.size() > K) maxHeap.poll();
        }

        List<int[]> rst = new ArrayList<>();
        while (! maxHeap.isEmpty()) {
            int[] pair = maxHeap.poll();
            rst.add(new int[]{pair[0], pair[1]});
        }
        // reverse to ensure in rst: dist is ASC
        Collections.reverse(rst);
        return rst;
    }

    public static void main(String[] args) {
        List<int[]> points = new ArrayList<>(List.of(new int[]{2, 3}, new int[]{3, 4}, new int[]{1, -3}, new int[]{-2, 2}));
        int K = 2;
        List<int[]> rst = kClosest(points, K);
        for (int[] item : rst) {
            System.out.println(Arrays.toString(item));
        }
    }
}
