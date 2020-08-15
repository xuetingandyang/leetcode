package codesignal;

//一个没有重复数字的数组，将这个数组所有相邻的数，打乱顺序生成一个int[2]放进一个array里面, 再打乱顺序给你，让你返回原数组。(倒序也是正确的)
//例子:
//[3, 5, 1, 2, 4]
//有相邻pair [[3, 5], [5, 1], [1, 2], [2, 4]]
//打乱顺序后为[[4, 2], [1, 2], [5, 3], [5, 1]]
//
//给你[[4, 2], [1, 2], [5, 3], [5, 1]],
// 你需要返回[3, 5, 1, 2, 4] 或[4, 2, 1, 5, 3]
//
//(建个adjacent list, 挨个连就行)
//第4题的思路，可以根据pair 建立无向图，度为1的为头尾节点，之后根据边顺序链接就行了

import java.util.*;
import java.util.List;

public class RecoverShufflePairs {

    public List<Integer> recoverPairs(List<List<Integer>> pairs) {

        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (List<Integer> pair : pairs) {
            graph.putIfAbsent(pair.get(0), new ArrayList<>());
            graph.putIfAbsent(pair.get(1), new ArrayList<>());
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }

        for (Integer node : graph.keySet()) {
            if (graph.get(node).size() == 1) {
                result.add(node);
                int newNode = graph.get(node).get(0);
                result.add(newNode);
                while (graph.get(newNode).size() != 1) {
                    List<Integer> neighbors = graph.get(newNode);
                    for (int n : neighbors) {
                        if (n != node) {
                            result.add(n);
                            node = newNode;
                            newNode = n;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> pairs = Arrays.asList(
            Arrays.asList(4,2),
            Arrays.asList(1,2),
            Arrays.asList(5,3),
            Arrays.asList(5,1)
        );
        List<List<Integer>> pairs2 = Arrays.asList(
                Arrays.asList(2,1),
                Arrays.asList(2,3)
        );
        RecoverShufflePairs obj = new RecoverShufflePairs();
        List<Integer> nums = obj.recoverPairs(pairs);
        System.out.println(nums);

    }
}
