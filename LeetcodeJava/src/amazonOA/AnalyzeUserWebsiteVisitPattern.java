package amazonOA;

//    We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
//    A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.
//    (The websites in a 3-sequence are not necessarily distinct.)
//
//    Find the 3-sequence visited by the largest number of users.
//    If there is more than one solution, return the lexicographically smallest such 3-sequence.
//    Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
//           timestamp = [1,2,3,4,5,6,7,8,9,10],
//           website = ["home","about","career","home","cart","maps","home","home","about","career"]
//    Output: ["home","about","career"]
//  Explanation:
//    The tuples in this example are:
//    ["joe", 1, "home"]
//    ["joe", 2, "about"]
//    ["joe", 3, "career"]
//    ["james", 4, "home"]
//    ["james", 5, "cart"]
//    ["james", 6, "maps"]
//    ["james", 7, "home"]
//    ["mary", 8, "home"]
//    ["mary", 9, "about"]
//    ["mary", 10, "career"]
//    The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
//    The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
//    The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
//    The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
//    The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
//  Note:
//    3 <= N = username.length = timestamp.length = website.length <= 50
//    1 <= username[i].length <= 10
//    0 <= timestamp[i] <= 10^9
//    1 <= website[i].length <= 10
//    Both username[i] and website[i] contain only lowercase characters.
//    It is guaranteed that there is at least one user who visited at least 3 websites.
//    No user visits two websites at the same time.

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    private class Pair {
        int time;
        String web;
        Pair (int time, String web) {
            this.time = time;
            this.web = web;
        }
    }

    private String rst = "";
    private int maxCount = 0;

    private void findUniqueThreeWebs(Map<String, Integer> count, List<Pair> list) {
        // find unique 3-pair
        // set: "web1 web2 web3"
        Set<String> set = new HashSet<>(); // to avoid visit same 3-pair again

        for (int i = 0; i < list.size(); i ++) {
            for (int j = i + 1; j < list.size(); j ++) {
                for (int k = j + 1; k < list.size(); k ++) {
                    String webs = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                    if (!set.contains(webs)) {
                        count.put(webs, count.getOrDefault(webs, 0) + 1);
                        set.add(webs);
                    }
                    if (maxCount < count.get(webs) || (maxCount == count.get(webs) && webs.compareTo(rst) < 0) ) {
                        maxCount = count.get(webs);
                        rst = webs;
                    }
                }
            }
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        // 1. get a map of (user, [(time, web), (time, web), ...])
        // 2. for each user, count unique 3-pair => get the maxCount

        Map<String, List<Pair>> userMap = new HashMap<>();
        for (int i = 0; i < username.length; i ++) {
            userMap.putIfAbsent(username[i], new ArrayList<>());
            userMap.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }

        // count: key:3-WebPair, value: freq
        Map<String, Integer> count = new HashMap<>();
        for (String user : userMap.keySet()) {
            List<Pair> list = userMap.get(user);
            Collections.sort(list, (a, b) -> a.time - b.time);
            // find unique 3-pair
            findUniqueThreeWebs(count, list);
        }
        // System.out.print(count);

        List<String> rstList = List.of(rst.split(" "));

        return rstList;
    }
}
