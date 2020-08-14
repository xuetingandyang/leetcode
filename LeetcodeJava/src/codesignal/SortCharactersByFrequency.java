package codesignal;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//451. Sort Characters By Frequency
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // order in descending order
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> e = pq.poll();
            for (int i = 0; i < e.getValue(); i++) {
                sb.append(e.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "tree";
        SortCharactersByFrequency sortCharactersByFrequency = new SortCharactersByFrequency();
        String rst = sortCharactersByFrequency.frequencySort(s);
        System.out.println(rst);
    }
}
