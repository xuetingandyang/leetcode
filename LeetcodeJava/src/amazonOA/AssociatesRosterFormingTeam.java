package amazonOA;

// find the # of teams that fulfilling the criteria
// input:
//      int num: # of associates
//      List<Integer> skills; skill levels of associates
//      int minAssociates: min # of team members required
//      int minLevel, maxLevel: lower/upper limit for skill level
// output: int : # of possible teams
// eg: input: num = 6, minAssociates = 3, minLevel = 4, maxLevel = 10
// skills = [12, 4, 6, 13, 5, 10]
// output: 5
// only 4, 5, 6, 10 are satisfied associates
// they can form (4,5,6), (4,6,10), (5,6,10), (4,5,10), (4,5,6,10) => 5

import java.util.*;

public class AssociatesRosterFormingTeam {
    private int count = 0;
    private void traverse(List<Integer> satisfied, int minAssociates, int startIdx, List<Integer> temp) {

        if (temp.size() >= minAssociates) count ++;
        // recursion exit
        // (if we exit when temp.size() >= minAssociates, we will only get combinations with size of minAssociates,
        // because when = minAssociates, return, we will never go into > minAssociates.
        // so we still should exit when reach satisfied.size()
        // and update count++ in other if statement

        if (temp.size() == satisfied.size()) return;
        // recursion split
        for (int i = startIdx; i < satisfied.size(); i ++) {
            temp.add(satisfied.get(i));
            traverse(satisfied, minAssociates, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public int countPossibleTeams(int num, List<Integer> skills, int minAssociates, int minLevel, int maxLevel) {

        if (num < minAssociates) return 0;
        // find satisfied associates
        Collections.sort(skills);
        List<Integer> satisfied = new ArrayList<>();
        for (int skill : skills) {
            if (skill >= minLevel && skill <= maxLevel) {
                satisfied.add(skill);
            }
        }
        // DFS to find all possible teams
//        traverse(satisfied, minAssociates, 0, new ArrayList<>());

        // or: directly use combination formula to count the number
        // since we do not need the concrete team, use combination formula is quicker than DFS
        int numSatisfy = satisfied.size();
        for (int i = minAssociates; i <= numSatisfy; i++) {
            // count = C(5,3) + C(5, 4) + C(5, 5) (if minAssociates = 3, satisfied.size() = 5)
            int denominator = 1, nominator = 1;
            for (int j = numSatisfy; j > numSatisfy - i; j --) denominator *= j;
            for (int j = 1; j <= i; j ++) nominator *= j;
            count += denominator / nominator;
        }

        return count;
    }

    public static void main(String[] args) {
        int num = 6;
        int minAssociates = 3, minlevel = 4, maxLevel = 12;
        List<Integer> skills = new ArrayList<>(List.of(12, 4, 6, 13, 5, 10));
        System.out.println(new AssociatesRosterFormingTeam().countPossibleTeams(num, skills, minAssociates, minlevel, maxLevel));
    }
}
