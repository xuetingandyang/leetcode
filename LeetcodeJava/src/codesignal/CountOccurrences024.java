package codesignal;

// count 0, 2, 4, occurrences in number n
// n = 10: 0:[0,10], 2:[2], 4:[4] => 2+1+1=4
// n = 22: 0:[0,10,20], 2:[2,12,20,21,22], 4:[4,14]
// => 3+6 (22 has two '2's) + 2 = 11

public class CountOccurrences024 {
    public int countOcc(int n) {
        int count = 0;
        for (int i = 0; i <= n ; i ++) {
            char[] chars = String.valueOf(i).toCharArray();
            for (char c : chars) {
                if (c == '0' || c == '2' || c == '4') count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountOccurrences024 obj = new CountOccurrences024();
        int count = obj.countOcc(24);
        System.out.println(count);
    }
}
