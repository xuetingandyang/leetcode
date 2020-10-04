package codility;

import java.util.HashMap;
import java.util.Map;

/* string S only contains 'a' and 'b', split S into 3 parts
* if each part has same number of 'a' => return different partitions
* else return 0
* */

public class StringPartitionIntoThreeWithSameNumOfA {
    public static void main(String[] args) {
        String s1 = "babaa";    // [ba,ba,a], [bab,a,a] => 2
        String s2 = "ababa";    // [a,ba,ba],[a,bab,a], [ab,a,ba], [ab,ab,a] => 4
        String s3 = "aba";      // 0
        String s4 = "bbbbb";    // 6
        System.out.println(solve(s1));
        System.out.println(solve(s2));
        System.out.println(solve(s3));
        System.out.println(solve(s4));
    }
    /* DFS can get all possible solutions -> DP
    * map: key: # of 'a', value: string length of previous 'a' to current 'a'
    * We will only use map(k) (k = # of 'a' in each partition)
    * for 2k: result += map(k)
    *
    * note: if K exists && cnt %3 ==0, then rst > 0
    * */
    private static int solve(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            cnt += c == 'a' ? 1 : 0;
        }
        if (cnt %3 != 0) return 0;

        int res = 0, k = cnt/3, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            sum += s.charAt(i) == 'a' ? 1 : 0;
            if(sum == 2*k && map.containsKey(k) && i < s.length() - 1 && i > 0) {
                res += map.get(k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
