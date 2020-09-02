package amazonOA;

//    You are on a flight and wanna watch two movies during this flight.
//    You are given List<Integer> 'movieDurations' which includes all the movie durations.
//    You are also given the duration of the flight which is 'd' in minutes.
//    Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).
//
//    Find the pair of movies with the longest total duration and return they indexes.
//    If multiple found, return the pair with the longest movie.
//
//    Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
//    Output: [0, 6]
//    Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)


import java.util.*;

public class MoviesonFlight {
    // time: O(NlogN+N) = O(NlogN)
    // space: O(N)
    private class Pair{
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int[] get2SumClosest(int[] movies, int time) {
        int n = movies.length;
        Pair[] moviePairs = new Pair[n];
        for (int i = 0; i < n; i ++) {
            moviePairs[i] = new Pair(movies[i], i);
        }

        int[] rst = new int[2];
        Arrays.sort(moviePairs, (a, b) -> a.val - b.val);
        int i = 0, j = n - 1;
        while (i < j) {
            int sum = moviePairs[i].val + moviePairs[j].val;
            int endTime = time - 30;
            if (sum < endTime) {
                rst[0] = moviePairs[i].idx;
                rst[1] = moviePairs[j].idx;
                i ++;
            } else if (sum > endTime) {
                j --;
            } else {
                rst[0] = moviePairs[i].idx;
                rst[1] = moviePairs[j].idx;
                return rst;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] movies1 = {90, 85, 75, 60, 120, 150, 125};
        int d1 = 250;
        int[] movies2 = {90, 85, 75, 60, 155, 150, 125};
        int d2 = 250;
        int[] movies3 = {90, 85, 75, 60, 120, 110, 110, 150, 125};
        int d3 = 250;
        System.out.println(Arrays.toString(new MoviesonFlight().get2SumClosest(movies1, d1)));
        System.out.println(Arrays.toString(new MoviesonFlight().get2SumClosest(movies2, d2)));
        System.out.println(Arrays.toString(new MoviesonFlight().get2SumClosest(movies3, d3)));
    }
}
