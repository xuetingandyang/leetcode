package codesignal;
//leetcode 541. Reverse String II
public class ReverseStringII {
    public String reverse(String s){
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        return sb.reverse().toString();
    }
    public String reverseStr0(String s, int k) {
        if(s.length() < k){
            return reverse(s);
        }
        if(s.length() >= k && s.length() < 2 * k){
            return reverse(s.substring(0, k)) + s.substring(k);
        }
        return reverse(s.substring(0, k)) + s.substring(k, 2* k) + reverseStr(s.substring(2*k),k);
    }

    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        ReverseStringII obj = new ReverseStringII();
        String rst = obj.reverseStr(s, k);
        System.out.println(rst);
    }
}
