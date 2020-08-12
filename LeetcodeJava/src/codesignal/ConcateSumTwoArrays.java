package codesignal;
// array a and b,
// iterate x from left to right in a,
// and y from right to left in b at the same time.
// concatenate x and y, return the sum.
//    eg: a = [12, 30], b = [112, 8]
//    a[0] + b[-1] = "128"
//    a[1] + b[-2] = "30112"
//    add: 128 + 30112 = 30240

public class ConcateSumTwoArrays {

    public int sum(int[] a, int[] b){
        int rst = 0;
        if(a.length != b.length){
            return rst;
        }
        for (int i = 0; i < a.length; i ++) {
            rst += concatenate(a[i], b[a.length - i - 1]);
        }
        return rst;
    }

    public int concatenate(int n1, int n2){
        int n3 = n2;
        while (n2 != 0 ) {
            n1 = n1 * 10;
            n2 /= 10;
        }
        return n1 + n3;
    }

    public static void main(String[] args) {
        int[] a = {12, 30}, b = {112, 45};
        ConcateSumTwoArrays concateSumTwoArrays = new ConcateSumTwoArrays();
        int rst = concateSumTwoArrays.sum(a, b);
        System.out.println(rst);
    }
}
