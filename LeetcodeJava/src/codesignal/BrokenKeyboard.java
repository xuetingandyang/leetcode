// 键盘的部分英文字母键坏了（注意只有字母键坏了）
// 给定一个String 和 一个char Array（没坏的字母键），输出String中能打出的字符串数。
//    栗子：
//    input “hello, world!”,  ['i','e','o','l','h']; output: 1 (只能打出 hello 这个单词）
//    input “5 + 3 = 8” []; output: 5 (没有英文字母， 5， +， 3， =， 8 都可以打出）


package codesignal;

import java.util.HashSet;
import java.util.Set;

public class BrokenKeyboard {

    public int numOfWords(String s, char[] keys){
        if(keys == null || keys.length == 0 || s == null || s.length() == 0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        for(char c: keys){
            set.add(c);
        }
        String[] words = s.split(" ");
        int rst = 0;
        search: for (String word : words) {
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c) && ! set.contains(c)) {
                    continue search;
                }
            }
            rst++;
        }
        return rst;
    }

    public static void main(String[] args) {
        String s = "5 + 3 = 8";
//        String s = "hello, world";
        char[] keys = {'i','e','o','l','h'};
        BrokenKeyboard brokenKeyboard = new BrokenKeyboard();
        int rst = brokenKeyboard.numOfWords(s, keys);
        System.out.println(rst);
    }
}
