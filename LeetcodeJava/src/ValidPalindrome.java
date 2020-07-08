// Given a string, determine if it is a palindrome,
// considering only alphanumeric characters and ignoring cases.
//    Note: For the purpose of this problem,
//    we define empty string as valid palindrome.

//    Input: "A man, a plan, a canal: Panama"
//    Output: true

//    Input: "race a car"
//    Output: false

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        // Two Pointers: p1 starts from head, p2 starts from tail
        int p1 = 0, p2 = s.length() - 1;

        while (p1 < p2) {
            char c1 = s.charAt(p1);
            char c2 = s.charAt(p2);
            // check if c1, c2
            if ( !Character.isLetterOrDigit(c1) ) {
                p1 ++;
            } else if ( ! Character.isLetterOrDigit(c2)) {
                p2 --;
            } else {
                // convert to lower case
                if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                    return false;
                }
                p1 ++;
                p2 --;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        ValidPalindrome Solution = new ValidPalindrome();
        boolean isPal = Solution.isPalindrome(str);
        System.out.println(isPal);
    }
}
