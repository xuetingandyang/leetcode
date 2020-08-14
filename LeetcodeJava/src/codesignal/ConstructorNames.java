package codesignal;

import java.util.HashMap;
import java.util.Map;

//Give two String a and b, is there any possible to replace the same frequency character
//compare 两个 string，只有小写字母。 每个 stirng 内部可以任意换位置，所以位置不重0要。
// 每个 string 内部两个 letter 出现的频率也可以互换，
// 所以这题只需要两个 string 每个frequency 出现的次数要一样。
// 比如“babzccc” 和 “bbazzcz” 就返回“true”，因为 z 和 c 可以互换频率。
// 但是“babzcccm” 和 “bbazzczl” 就不一样，因为 m 在第一个里出现过，第二个里没有出现过。
// If two strings are close enough.
// Given two rules to define two strings are close enough.
//  1. you can swap neighbor char any times. Ex. "abb" -> "bba"
//  2. If two strings have the same character, then you can change the character into another.
//    Ex. If both strings contain "a" and "b",
//       you can change all "a"s in the first string
//       or change all "b"s in the first string. same as the second string
//    Ex.
//  Input: S1 = "babzccc", S2 = "abbzczz"
//  Output: True
// Sol: HashMap<Character, Integer> counts
// Check if keySet() equals()
// HashMap<Integer, Integer> counts of countr, check if the same
public class ConstructorNames {

    public boolean check(String a, String b){
        if(a == null && b == null){
            return true;
        }
        if(a == null || b == null){
            return false;
        }
        if(a.length() != b.length()){
            return false;
        }
        Map<Character, Integer> mapA = new HashMap<>();
        Map<Character, Integer> mapB = new HashMap<>();
        for(Character character: a.toCharArray()){
            mapA.put(character, mapA.getOrDefault(character, 0) + 1);
        }
        for(Character character: b.toCharArray()){
            mapB.put(character, mapB.getOrDefault(character, 0) + 1);
        }
        for(Character character: mapA.keySet()){
            if(! mapB.containsKey(character)){
                return false;
            }
        }

        Map<Integer, Integer> countA = new HashMap<>();
        Map<Integer, Integer> countB = new HashMap<>();
        for(Map.Entry<Character, Integer> entry: mapA.entrySet()){
            countA.put(entry.getValue(), countA.getOrDefault(entry.getValue(), 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry: mapB.entrySet()){
            countB.put(entry.getValue(), countB.getOrDefault(entry.getValue(), 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry: countA.entrySet()){
            if( ! entry.getValue().equals(countB.get(entry.getKey()))){
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        String a ="babzccc", b = "abbzczz", c="zbbaaac";
        ConstructorNames constructorNames = new ConstructorNames();
        boolean rst = constructorNames.check(a, c);
        System.out.println(rst);
    }
}
