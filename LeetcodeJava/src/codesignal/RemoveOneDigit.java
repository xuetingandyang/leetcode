package codesignal;
//remove one digit char from string s or t, so that s < t;
//output the number of ways to remove the character from the string.

public class RemoveOneDigit {
    public int removeOneDigit(String s, String t){
        int rst = 0;
        for(int i = 0; i < s.length(); i++){
            StringBuffer newS = new StringBuffer(s);
            newS.replace(i, i+1, "");
            if(newS.toString().compareTo(t) > 0){
                rst ++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        String s = "good", t = "bad";
        RemoveOneDigit removeOneDigit = new RemoveOneDigit();
        int rst = removeOneDigit.removeOneDigit(s, t);
        System.out.println(rst);
    }
}
