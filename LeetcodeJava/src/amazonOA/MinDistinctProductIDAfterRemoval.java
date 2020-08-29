package amazonOA;
/* Given list of Product IDs (n), remove m from it
* so that you have the smallest distinct # of products
* Input:
*   int num: # of products in the list (n)
*   List<Integer> ids: product IDs
*   int rem: max number of removals  (m)
* Output:
*   int: min number of DISTINCT product IDs remaining after removals
*
* eg: num = 6, rem = 2
*     ids = [1, 1, 1, 2, 3, 2]
* output: 2
*
* we can max remove 2 items
* remove 2 items: [1,3] or [1,2] or [1,2,3]
* so the min # of distinct IDs = 2
* */

// map: (id, freq)
// (1,3), (2,2), (3,1)
// PQ(freq) sort by freq
// if Count <= rem, update curCount += PQ.poll()
// if updated count = rem, return remaining pq.size
//                  >    (do not remove all of the PQ.peek), so return remaining pq.size() + 1

// n: #of productIDs, m: rem, D: # of distinct product IDs
// time: O(n + DlogD + m) -> O(n)
// space: O(n + D)

import java.util.*;

public class MinDistinctProductIDAfterRemoval {
    public int minDistinct(int num, int rem, List<Integer> ids) {

        if (rem >= num) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int id : ids) map.put(id, map.getOrDefault(id, 0) + 1);

        Queue<Integer> pq = new PriorityQueue<>();
        pq.addAll(map.values());

        int count = 0;
        while ( !pq.isEmpty() && count <= rem) {
            int freq = pq.poll();
            count += freq;
        }
        if (count > rem) return pq.size() + 1;
        else return pq.size();
    }

    public static void main(String[] args) {
        int num = 10, rem = 3;
        List<Integer> ids = new ArrayList<>(List.of(1,1,1,1,2,3,2,4,5,4));
        System.out.println(new MinDistinctProductIDAfterRemoval().minDistinct(num, rem, ids));
    }
}
