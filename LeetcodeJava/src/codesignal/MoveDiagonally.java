package codesignal;

// start from index 1
// Input: maze (n*m), src=(x1, y1), dest=(x2,y2)
// start at src, direction=(x+1, y+1)
//  if reaches edge -> reverse one direction
//  if reaches corner node -> reverse both directions
// so if reach n-th row    -> (x+1, y-1)
//             m-th column -> (x-1, y+1)
//            (n,m) corner -> (x-1, y-1)
// if cannot reach dest, return -1
// n=m=5, x1=2,y1=1, x2=1,y2=2 => output 7 steps

// if cannot reach dest, it will finally go back to src

public class MoveDiagonally {
    public static int movingDiagonally(int n, int m, int x1, int y1, int x2, int y2) {

        if (x1 == x2 && y1 == y2) return 0;

        int dirX = 1, dirY = 1;
        int x = x1 - 1, y = y1 - 1; // cur position
        int steps = 0;

        if (x == y) {
            if (x2 == y2) {
                // src and dest at same diagonal is a special case
                if (x2 > x1) return x2 - x1;
                else return (n-x1 + n-x2);
            } else {
                // if dest not on diagonal, cannot reach
                return -1;
            }
        }

        while ((x != x1 - 1 || y != y1 - 1) || steps == 0 ) {
            // while not go back to src
            if (x == n - 1 || x == 0) dirX = -dirX;
            if (y == m - 1 || y == 0) dirY = -dirY;
            if (x == x2 - 1 && y == y2 - 1) return steps;

            x += dirX;
            y += dirY;
            steps ++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 5, m = 5;
//        int x1 = 5, y1 = 4;
//        int x2 = 4, y2 = 3;
        int x1 = 4, y1 = 4;
        int x2 = 2, y2 = 2;
        int steps = movingDiagonally(n, m, x1, y1, x2, y2);
        System.out.println(steps);
    }
}
