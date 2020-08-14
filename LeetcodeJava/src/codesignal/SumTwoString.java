package codesignal;

//sum of string，给两串字符串，每个 char 就是一个 digit，
// 然后从后往前加起来，把结果放到一个字符串输出，
//eg: "99" + "99" = "1818"
//     "99" + "1" = "910"
//如果写 Java 的话最好用 StringBuilder, String 会 TLE

public class SumTwoString {
    public String sum(String s1, String s2){
        StringBuffer sb = new StringBuffer();
        int len1 = s1.length() - 1, len2 = s2.length() - 1;
        while (len1 >= 0 || len2 >= 0) {
            int i1 = 0, i2 = 0;
            if ( len1 >= 0 ) i1 = s1.charAt(len1) - '0';
            if ( len2 >= 0 ) i2 = s2.charAt(len2) - '0';
            int sum = i1 + i2;
            sb.insert(0, String.valueOf(sum));
            len1--;
            len2--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "99", s2 = "18";
        SumTwoString sumTwoString = new SumTwoString();
        String rst = sumTwoString.sum(s1, s2);
        System.out.println(rst);
    }
}
