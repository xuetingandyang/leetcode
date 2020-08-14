package codesignal;

import java.util.HashSet;
import java.util.Set;
//Give a number n and digit number k find all serial substring is able to divisible n.
//    给一个数字, 和一个 k 值(表示除数位数), 看能用多少个 sub number 整除
//    Input: n = 120, k = 2
//    Output: 2
//    Explain:
//    120 -> 12 and 20
//    120 % 12 == 0 (O)
//    120 % 20 == 0 (O)
//
//    Input: n = 555, k = 1;
//    Output: 1
//    Explain:
//    555 -> 5, 5 and 5 (Duplicate so only count one 5)
//    555 % 5 == 0 (O)
//
//    Input: n = 2345, k = 2
//    Output: 0
//    Explain:
//    2345 -> 23, 34, 45
//    2345 % 23 != 0 (X)
//    2345 % 34 != 0 (X)
//    2345 % 45 != 0 (X)

public class DivisorSubstrings {

    public int divide(int number, int k){
        String s = String.valueOf(number);
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < s.length() - k + 1; i++){
            String sbStr = s.substring(i, i + k);
            int num = Integer.parseInt(sbStr);
            if (num != 0 && number % num == 0) {
                set.add(num);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int number = 120;
        int k = 1;
        DivisorSubstrings divisorSubstrings = new DivisorSubstrings();
        int rst = divisorSubstrings.divide(number, k);
        System.out.println(rst);
    }
}
