// Write a function that reverses a string.
// The input string is given as an array of characters char[].
//
//    Do not allocate extra space for another array,
//    you must do this by modifying the input array in-place with O(1) extra memory.
//    You may assume all the characters consist of printable ascii characters.
//
//    Input: ["h","e","l","l","o"]
//    Output: ["o","l","l","e","h"]

//    Input: ["H","a","n","n","a","h"]
//    Output: ["h","a","n","n","a","H"]

public class ReverseString {
    public void reverseString(char[] s) {
        // Two Pointers: p1 starts from head, p2 starts from tail
        // switch p1 and p2

        int p1 = 0, p2 = s.length - 1;
        while (p1 < p2) {
            // switch
            char temp = s[p1];
            s[p1] = s[p2];
            s[p2] = temp;

            // move next step
            p1++; p2--;
        }
    }

    public static void main(String[] args) {
        char[] s = new char[] {'H','a','n','n','a','h'};
        ReverseString Solution = new ReverseString();
        Solution.reverseString(s);
        System.out.println(s[0]);

    }
}
