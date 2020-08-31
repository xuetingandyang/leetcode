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
        // Use Deque to find the minima in each window, then find the max of those minimum
        // time: O(N) as each element is put and polled once.
        // space: O(N)

        // Deque(double-ended queue), poll & add first/last is O(1)
        // Deque(save index), always keep the peekFirst = min in deque
        // 1. deque keep only elements in [i-(k-1), i] -> while size > k, deque.pollFirst()
        //    If an element in the deque and it is out of i-(k-1), we discard them.
        //    We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array
        // 2. remove idx that nums[i] < nums[idx] -> while newAddedNum < peekLast, pollLast()
        //   (since 'idx' will never be the min, 'i' will always be a better candidate)
        // 3. add current 'i' into deque
        // At each step the head of the deque is the min element in [i-(k-1),i]
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

    public int findMaxDiskSpaceBruteForce(int numComputer, int segmentLength, List<Integer> hardDiskSpace) {
        // record min in each window
        int[] minDisks = new int[numComputer - segmentLength + 1];

        for (int i = 0; i < numComputer - segmentLength + 1; i ++) {
            int minVal = Integer.MAX_VALUE;
            for (int j = i; j <= i + segmentLength - 1; j ++) {
                if ( hardDiskSpace.get(j) < minVal)
                    minVal = hardDiskSpace.get(j);
            }
            minDisks[i] = minVal;
        }
        return Arrays.stream(minDisks).max().getAsInt();
    }

    public static void main(String[] args) {
        int numComputer = 6, segmentLength = 1;
        List<Integer> hardDiskSpace = new ArrayList<>(List.of(8, 2, 4, 6, 1, 6));
        System.out.println(new AvailableDiskSpaceOfComputer().findMaxDiskSpace(numComputer, segmentLength, hardDiskSpace));
        System.out.println(new AvailableDiskSpaceOfComputer().findMaxDiskSpaceBruteForce(numComputer, segmentLength, hardDiskSpace));
    }
}
