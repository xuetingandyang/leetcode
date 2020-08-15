package codesignal;

// 给一串命令 和一个 int  n， 然后return一个length 为 n的 string 我们叫m ，
// 命令有两种 “L” 和“C{数字}”， L 是把 m中最左边的 “0”改成“1”， “C{数字}”是把 m 对应数字index的1 改成 0.
//举个例子  n=5   命令为{L,L,L C1，L}
//开始前： 00000
//第一个命令 L 后 ： 10000
//第二个命令L 后 ：  11000
//第三个命令 L 后： 11100
//第四个命令 C1 后： 10100
//第五个命令 L 后： 11100， （注意L 是把string中最左边的0变成1）

import java.util.Arrays;
import java.util.List;

public class LCnumOperation {

    private String commandL(String str) {
        return str;
    }

    public String lcOperate(List<String> commands, int n) {

        char[] chars = new char[n];
        for (int i = 0; i < n; i ++) {
            chars[i] = '0';
        }
//        String str = chars.toString();
        for (String command : commands) {
            if (command == "L") {
                // find the leftmost '0'
                for (int i = 0; i < n; i ++) {
                    if (chars[i] == '0') {
                        chars[i] = '1';
                        break;
                    }
                }
            } else if (command.startsWith("C")) {
                int idx = Integer.parseInt(command.substring(1, command.length()));
                chars[idx] = '0';
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        int n = 5;
        List<String> commands = Arrays.asList("L", "L", "L", "C1", "L");
        LCnumOperation obj = new LCnumOperation();
        String str = obj.lcOperate(commands, n);
        System.out.println(str);
    }
}
