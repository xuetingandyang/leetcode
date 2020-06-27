// Given two words (beginWord and endWord), and a dictionary's word list,
// find all shortest transformation sequence(s) from beginWord to endWord,
// such that:
//    Only one letter can be changed at a time
//    Each transformed word must exist in the word list.
//    Note that beginWord is not a transformed word.
//
//   Note:
//    Return an empty list if there is no such transformation sequence.
//    All words have the same length.
//    All words contain only lowercase alphabetic characters.
//    You may assume no duplicates in the word list.
//    You may assume beginWord and endWord are non-empty and are not the same.
//
//    Input:
//    beginWord = "hit",
//    endWord = "cog",
//    wordList = ["hot","dot","dog","lot","log","cog"]
//
//    Output:
//    [
//    ["hit","hot","dot","dog","cog"],
//    ["hit","hot","lot","log","cog"]
//    ]

//    Input:
//    beginWord = "hit"
//    endWord = "cog"
//    wordList = ["hot","dot","dog","lot","log"]
//
//    Output: []
//    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 1). Use BFS to find the shortest distance between start and end,
        //      tracing the distance of crossing nodes from start node to end node,
        //      and store node's next level neighbors to HashMap;
        // 2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap:
        //      compare if the distance of the next level node equals the distance of the current node + 1.


        /**
         * @param rst: result list to store final return list
         * @param graph: adjacency list of key - store all neighbors of each word
         *              (neighbor means all words in dictionary
         *              that only has one character difference with key)
         * @param dict: word dictionary, efficient for searching purpose
         * */
        List<List<String>> rst = new ArrayList<>();

        if (wordList == null || wordList.size() == 0) return rst;

        List<String> path = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> dict = new HashSet<>(wordList);

        buildGraph(beginWord, endWord, graph, dict);

        dfs(beginWord, endWord, graph, new ArrayList<String>(), rst);

        return rst;
    }


    private void buildGraph(String beginWord,
                            String endWord,
                            Map<String, List<String>> graph,
                            Set<String> dict) {

        Set<String> visited = new HashSet<>();
        Set<String> toVisit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        toVisit.add(beginWord);
        boolean foundEnd = false;

        while( ! queue.isEmpty() ) {
            visited.addAll(toVisit);
            toVisit.clear();
            int count = queue.size();

            for (int i = 0; i < count; i ++) {
                String word = queue.poll();
                List<String> children = getNextLevel(word, dict);

                // traverse all neighbors of current word,
                // update graph and queue for next level ladder
                for (String child: children) {
                    if (child.equals(endWord)) foundEnd = true;

                    if ( ! visited.contains(child)) {
                        if ( ! graph.containsKey(word)) {
                            graph.put(word, new ArrayList<>());
                        }
                        graph.get(word).add(child);
                    }

                    if ( ! visited.contains(child) && ! toVisit.contains(child)) {
                        queue.offer(child);
                        toVisit.add(child);
                    }
                }
            }
            if (foundEnd) break;
        }
    }

    private String replace(String word, char c, int index) {
        // replace word[index] to char c
        char[] chars = word.toCharArray();
        chars[index] = c;
        // deep copy
        return new String(chars);
    }


    private List<String> getNextLevel(String word, Set<String> dict) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < word.length(); i ++) {
            for (char j = 'a'; j < 'z'; j ++) {
                if (word.charAt(i) == j) continue;

                String newWord = replace(word, j, i);

                if (dict.contains(newWord)) result.add(newWord);
            }
        }
        return result;

    }


    // 1-recursion definition
    // Find all paths that start with curWord, end with endWord
    // possible elements are in the neighbors of curWord
    private void dfs(String curWord,
                     String endWord,
                     Map<String, List<String>> graph,
                     List<String> path,
                     List<List<String>> rst) {
        path.add(curWord);

        // 2-recursion exit
        if (curWord.equals(endWord)) {
            // deep copy
            rst.add(new ArrayList<String>(path));
        } else if (graph.containsKey(curWord)) {
            for (String nextWord: graph.get(curWord)) {
                dfs(nextWord, endWord, graph, path, rst);
            }
        }
        path.remove(path.size() - 1);
    }


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot","dot","dog","lot","log","cog");

        WordLadderII Solution = new WordLadderII();
        System.out.println(Solution.findLadders(beginWord, endWord, wordList));

    }
}
