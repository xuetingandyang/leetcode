package codesignal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 12345 => return 1-2+3-4+5 = 3

public class PlusMinus {
    public int cal(int num){
        // FILO: stack, since we get the num%10,
        // .e. 5,4,3,2,1 => push to stack => get 1,2,3,4,5
        Stack<Integer> stack = new Stack<>();
        while(num != 0){
            stack.push(num % 10);
            num /= 10;
        }
        int rst = 0;
        boolean plus = true;
        while( !stack.isEmpty() ){
            if (plus) {
                rst += stack.pop();
                plus = !plus;
            } else {
                rst -= stack.pop();
                plus = !plus;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int num = 12345;
        PlusMinus plusMinus = new PlusMinus();
        int rst = plusMinus.cal(num);
        System.out.println(rst);
    }
}
