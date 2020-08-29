package amazonOA;

/*
* input:
*   int numComputer
*   List<Integer> hardDiskSpace: list of hard disk spaces of computers
*   int segmentLength: length of Contiguous segment of computers
* output:
*   int : max available disk space among all the minima during analysis
* eg: numComputer = 3, segmentLength = 2
*     hardDiskSpace = [8,2,4]
* output 2
*
* segments: [8,2], [2,4]
* the analysis return minima : 2,2 => choose the max one => return 2
* */

import java.util.*;

public class AvailableDiskSpaceOfComputer {
    public int findMaxDiskSpace(int numComputer, int segmentLength, List<Integer> hardDiskSpace) {
        // deque
        Deque<Integer> deque = new ArrayDeque<>();
        int[] minDisks = new int[numComputer - segmentLength + 1];

        for (int i = 0; i < numComputer; i ++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - segmentLength + 1)
                deque.pollFirst();

            while (!deque.isEmpty() && hardDiskSpace.get(deque.peekLast()) > hardDiskSpace.get(i))
                deque.pollLast();

            deque.addLast(i);
            if (i >= segmentLength - 1)
                minDisks[i - segmentLength + 1] = hardDiskSpace.get(deque.peekFirst());
        }
        return Arrays.stream(minDisks).max().getAsInt();
    }

    public static void main(String[] args) {
        int numComputer = 6, segmentLength = 2;
        List<Integer> hardDiskSpace = new ArrayList<>(List.of(8, 2, 4, 6, 1, 6));
        System.out.println(new AvailableDiskSpaceOfComputer().findMaxDiskSpace(numComputer, segmentLength, hardDiskSpace));
    }
}
