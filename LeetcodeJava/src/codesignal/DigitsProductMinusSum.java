package codesignal;

// input: 123456
// product = 1*2*3*4*5*6 = 720
// sum 1+2+3+4+5+6 = 21
// output = product - sum = 720 - 21 = 699

// 1010 => 1*0*1*0 - 2 = -2

public class DigitsProductMinusSum {
    public int digitsProductMinusSum (int n) {
        String str = String.valueOf(n);
        int[] nums = new int[str.length()];

        for (int i = 0; i < str.length(); i ++) {
            nums[i] = str.charAt(i) - '0';
        }
        int product = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            product *= nums[i];
            sum += nums[i];
        }
        return product - sum;
    }

    public static void main(String[] args) {
        DigitsProductMinusSum obj = new DigitsProductMinusSum();
        int rst1 = obj.digitsProductMinusSum(123456);
        int rst2 = obj.digitsProductMinusSum(1010);
        System.out.println(rst1);
        System.out.println(rst2);
    }

}
