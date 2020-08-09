// Given a non-empty list of words, return the k most frequent elements.
//
//    Your answer should be sorted by frequency from highest to lowest.
//    If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
//    Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//    Output: ["i", "love"]
//    Explanation: "i" and "love" are the two most frequent words.
//    Note that "i" comes before "love" due to a lower alphabetical order.
//    Note:
//        You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//        Input words contain only lowercase letters.
//    Follow up:
//        Try to solve it in O(n log k) time and O(n) extra space.

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        // HashMap: (word, freq)
        // PriorityQueue (minHeap) of size K to keep topK.
        // (a, b) -> (a - b) (alphabetical)
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 1) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                k,
                (a, b) -> map.get(a).equals(map.get(b)) ? b.compareTo(a) : map.get(a) - map.get(b)
        );
        // Strings compare: b.compareTo(a) = b - a
        // eg: a = 'aaa', b = 'bbb' => we want 'aaa' ranks higher than 'bbb'
        // => a.rank > b.rank => get a positive value
        // => b.compareTo(a) [>0] not a.compareTo(b) [<0]

        for (String word : map.keySet() ) {
            minHeap.add(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // convert to List
        List<String> rst = new ArrayList<>();
        while (! minHeap.isEmpty()) {
            rst.add(minHeap.poll());
        }
        Collections.reverse(rst);
        return rst;
    }
}
















