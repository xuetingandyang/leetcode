package strings;

//Rotate String
//Given a string and an offset, rotate string by offset. (rotate from left to right)

//Given "abcdefg".
//offset=0 => "abcdefg"
//offset=1 => "gabcdef"
//offset=2 => "fgabcde"
//offset=3 => "efgabcd"
//
//Challenge
//Rotate in-place with O(1) extra memory.

// 3-step rotate

public class RotateString {

    private static void reverse(char[] chars, int start, int end) {
        // reverse string '12345' -> '54321'
        while (start < end) {
            char c = chars[start];
            chars[start] = chars[end];
            chars[end] = c;
            start ++; end --;
        }
    }
    static void rotate(char[] chars, int offset) {
        if (chars == null || chars.length == 0) return;

        int n = chars.length;
        offset %= n;

        reverse(chars, 0, n - 1 - offset);
        reverse(chars, n - offset, n - 1);
        reverse(chars, 0, n - 1);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int offset = 2;
        char[] chars = s.toCharArray();
        rotate(chars, offset);
        System.out.println(chars);
    }
}
