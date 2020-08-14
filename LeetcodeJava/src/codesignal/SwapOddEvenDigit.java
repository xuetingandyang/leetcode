package codesignal;

import java.util.Stack;

// 123456 => swap even and odd digits
// 214365

public class SwapOddEvenDigit {

    public int swap(int num){
        // use stack to get 1,2,3,4,5,6
        // FILO, order when 'in' is 654321, so order when 'out' is 123456
        Stack<Integer> stack = new Stack<>();
        while(num != 0){
            stack.push(num % 10);
            num /= 10;
        }
        int rst = 0;
        while(stack.size() >= 2){
            int first = stack.pop(); // 1, 3, 5
            int second = stack.pop();// 2, 4, 6
            rst = rst * 100 + second * 10 + first;
        }
        if (stack.size() == 1) {
            rst = rst * 10 + stack.pop();
        }
        return rst;
    }

    public static void main(String[] args) {
        int num = 1234567;
        SwapOddEvenDigit swapOddEvenDigit = new SwapOddEvenDigit();
        int rst = swapOddEvenDigit.swap(num);
        System.out.println(rst);
    }
}
