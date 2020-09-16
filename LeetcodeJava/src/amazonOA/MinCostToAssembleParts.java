package amazonOA;
// 3. 零件装配/合并part
// 给一个array表示零件的size，每次可以把两个组装在一起。每次组装的cost是两个size之和。就是数字可能重复
// 新组装出来零件的size也是这两个小零件size之和。问把所有零件组装成一整个的min cost。
// eg: [1,2,3,1,2,5] => minCost = 31
// assemble [1,1], then parts:[2,2,2,3,5], cost = 1+1 = 2
// assemble [2,2]             [2,3,4,5]    cost = 2+4 = 6
//          [2,3]             [4,5,5]             6+5 = 11
//          [4,5]             [5,9]               11+9 = 20
//          [5,9]             []                  20+14 = 34

import java.util.PriorityQueue;
import java.util.Queue;

public class MinCostToAssembleParts {
    public static int minSum(int[] parts) {
        // every time assemble 2 MINs, then resort current parts
        if (parts == null || parts.length == 0) return 0;

        Queue<Integer> pq = new PriorityQueue<>();
        for (int part : parts) pq.add(part);

        int cost = 0;
        while (!pq.isEmpty()) {
            int newPart = pq.poll() + pq.poll();
            cost += newPart;
            if (pq.isEmpty()) break;
            pq.add(newPart);
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] parts = {1,2,3,1,2,5};
        System.out.println("Answer: 34 Actual: " + minSum(parts));
    }
}
