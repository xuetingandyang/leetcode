package codesignal;

import java.util.*;
import java.util.stream.IntStream;

// select 3 players in players array to have sum of them = k
// {-1, 0, 1, 2, -1, -4}, k= 0
// output the indices of select players:
// [[0,1,2], [0,3,4], [1,2,4]]

// like 3Sum in leetcode

public class FormingTeams {
    public List<List<Integer>> formTeam(int k, int[] players){
        // save sorted original indices
        int[] sortedIndices = IntStream
                .range(0, players.length).boxed()
                .sorted((i, j) -> players[i] - players[j])
                .mapToInt(i -> i).toArray();

        Arrays.sort(players);

        List<List<Integer>> rst = new ArrayList<>();

        for(int i = 0; i < players.length; i++){
            // TwoPointers find 2sum = k-players[i]
            int target = k - players[i];
            int l = i + 1, h = players.length - 1;
            while(l < h){
                if(players[l] + players[h] == target){
                    rst.add(Arrays.asList(sortedIndices[i], sortedIndices[h], sortedIndices[l]));
                    // if 'low' has same value, then jump to next same 'low' value, and keep 'high' unchanged
                    if (l + 1 < h && players[l] == players[l + 1]) l++;
                    else if (h - 1 > l && players[h] == players[h - 1]) h--;
                    else {
                        l++;
                        h--;
                    }
                }
                else if(players[l] + players[h] > target) h--;
                else l++;
            }
        }
        return rst;
    }


    public List<List<Integer>> threeSum(int k, int[] nums) {
        // cannot solve 3Sum return idx with this method (time: O(N^2), space: O(N))
        Set<List<Integer>> res = new HashSet<>();
//        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        // seen:(nums[j], i) This indicates that we can now use nums[j] as a complement for nums[i].

        for (int i = 0; i < nums.length; ++i)
//            if (dups.add(nums[i])) {
            for (int j = i + 1; j < nums.length; ++j) {
                int complement = k - nums[i] - nums[j];
                if (seen.containsKey(complement) && seen.get(complement) == i) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
//                    Collections.sort(triplet);
                    res.add(triplet);
                }
                seen.put(nums[j], i);
            }
//            }
        return new ArrayList(res);
    }



    public static void main(String[] args) {
        int[] players = {-1, 0, 1, 2, -1, 1, -4};
        int k = 0;
        FormingTeams formingTeams = new FormingTeams();
//        List<List<Integer>> rst = formingTeams.threeSum(k, players);
        List<List<Integer>> rst = formingTeams.formTeam(k, players);
        System.out.println(rst);
    }
}
