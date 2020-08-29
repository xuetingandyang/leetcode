package amazonOA;

//find the nearest city that shares either x or y with the queried city
// if noo city share x or y, return NONE
// if 2 cites have same distance, choose the alphabetically smaller one
// ('ab' < 'aba' < 'abb')
// distance = differences of X + differences of y

// input:
//      int numOfCities, int numOfQueris
//      List<String> cities: name of each city[i]
//      List<Integer> xCoordinates, yCoordinates: x, y of each city[i]
//      List<String> queries: name of queried cities
// output:
//      List<String>: name of nearest city with queried city
// Note: all cities[i] & queries[i] are in [a-z, 0-9, -]
//       all cities have unique name & unique location

// eg: numOfCities = 3, numOfQueries = 3
//     cities = ["c1", "c2", "c3"], queries = ["c1","c2","c3"]
//     x = [3,2,1], y = [3,2,3]
// -> output [c3, NONE, c1]
// for c1, only c3 shares x => c3
// for c2, no city share x or y -> NONE
// for c3, only c1 share x => c1

import java.util.*;

public class NearestCitiesThatshareXorY {

    public List<String> findNearestCityBruteForce(int numOfCities,
                                                  List<String> cities,
                                                  List<Integer> xCoordinates,
                                                  List<Integer> yCoordinates,
                                                  int numOfQueries,
                                                  List<String> queries) {
        List<String> rst = new ArrayList<>();
        // time: n: #cities, m: #queries, L: length of city name
        // at most time, n > m >> L
        // time: O(m * n)
        // space: O(1)
        for (String query : queries) {
            int i = cities.indexOf(query);
            int minDist = Integer.MAX_VALUE, minCityIdx = -1;

            for (int j = 0; j < numOfCities; j++) {
                if (j != i) {
                    if (xCoordinates.get(j) == xCoordinates.get(i)) {
                        int dist = Math.abs(yCoordinates.get(j) - yCoordinates.get(i));
                        if ( dist < minDist || (dist == minDist && cities.get(j).compareTo(cities.get(minCityIdx)) < 0) ) {
                            minDist = dist;
                            minCityIdx = j;
                        }
                    }
                    else if (yCoordinates.get(j) == yCoordinates.get(i)) {
                        int dist = Math.abs(xCoordinates.get(j) - xCoordinates.get(i));
                        if ( dist < minDist || (dist == minDist && cities.get(j).compareTo(cities.get(minCityIdx)) < 0)) {
                            minDist = dist;
                            minCityIdx = j;
                        }
                    }
                }
            }
            String nearest = minCityIdx == -1 ? "NONE" : cities.get(minCityIdx);
            rst.add(nearest);
        }
        return rst;
    }


    public List<String> findNearestCity(int numOfCities,
                                        List<String> cities,
                                        List<Integer> xCoordinates,
                                        List<Integer> yCoordinates,
                                        int numOfQueries,
                                        List<String> queries) {
        // 感觉没减少多少时间复杂度，还是选Brute Force吧

        // m: #cities, n: #queris, L: cityNameLength, P: longest length of sameX or sameY
        // m > n > P
        // time: O(m + m*2P + n*L) = O(2mP + nL)
        // space: O(m + m + m) = O(m)

        List<String> rst = new ArrayList<>();

        // (x, [city index with x])
        Map<Integer, List<Integer>> sameX = new HashMap<>();
        Map<Integer, List<Integer>> sameY = new HashMap<>();

        for (int i = 0; i < numOfCities; i ++) {
            int x = xCoordinates.get(i), y = yCoordinates.get(i);
            if (!sameX.containsKey(x)) sameX.put(x, new ArrayList<>());
            sameX.get(x).add(i);
            if (!sameY.containsKey(y)) sameY.put(y, new ArrayList<>());
            sameY.get(y).add(i);
        }

        // (city, nearestCity)
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < numOfCities; i ++) {
            int x = xCoordinates.get(i), y = yCoordinates.get(i);

            int minDist = Integer.MAX_VALUE, minIdx = -1;
            if (sameX.containsKey(x) && sameX.get(x).size() > 1) {
                for (int idx : sameX.get(x)) {
                    if (idx == i) continue;
                    int dist = Math.abs(yCoordinates.get(idx) - yCoordinates.get(i));
                    if (dist < minDist || (dist == minDist && cities.get(idx).compareTo(cities.get(minIdx)) < 0)) {
                        minDist = dist;
                        minIdx = idx;
                    }
                }
            }
            if (sameY.containsKey(y) && sameY.get(y).size() > 1) {
                for (int idx : sameY.get(y)) {
                    if (idx == i) continue;
                    int dist = Math.abs(xCoordinates.get(idx) - xCoordinates.get(i));
                    if (dist < minDist || (dist == minDist && cities.get(idx).compareTo(cities.get(minIdx)) < 0) ) {
                        minDist = dist;
                        minIdx = idx;
                    }
                }
            }
            String nearest = minIdx == -1 ? "NONE" : cities.get(minIdx);
            map.put(cities.get(i), nearest);
        }

        for (String query : queries) {
            rst.add(map.get(query));
        }
        return rst;
    }

    public static void main(String[] args) {
//        int numOfCities = 3, numOfQueries = 3;
//        List<Integer> xCoordinates = new ArrayList<>(List.of(3,2,1));
//        List<Integer> yCoordinates = new ArrayList<>(List.of(3,2,3));
//        List<String> cities = new ArrayList<>(List.of("c1", "c2", "c3"));
//        List<String> queries = new ArrayList<>(List.of("c1", "c2", "c3"));

        int numOfCities = 5, numOfQueries = 5;
        List<Integer> xCoordinates = new ArrayList<>(List.of(3,3,1,5,3));
        List<Integer> yCoordinates = new ArrayList<>(List.of(3,5,3,3,1));
        List<String> cities = new ArrayList<>(List.of("ab", "abc", "a", "d", "c"));
        List<String> queries = new ArrayList<>(List.of("ab", "abc", "a", "d", "c"));

        List<String> rst = new NearestCitiesThatshareXorY().findNearestCity(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries);
//        List<String> rst = new NearestCitiesThatshareXorY().findNearestCityBruteForce(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries);
        for (String s : rst) System.out.println(s);
    }
}
