/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
 
class WordFreq {
    String word;
    int freq;
    
    WordFreq(String s, int f) {
        this.word = s;
        this.freq = f;
    }
} 
 
 
public class TopKFrequentWords {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            if (value == null || value.content == null) {
                return;
            }
            
            String[] words = value.content.split(" ");
            for (String word: words) {
                if (word.length() > 0) {
                    output.collect(word, 1);
                }
            }
        }
    }

    public static class Reduce {
        
        private int k = 0;
        private PriorityQueue<WordFreq> pq = null;
        
        private Comparator<WordFreq> pairComparator = new Comparator<WordFreq>() {
            public int compare(WordFreq p1, WordFreq p2) {
                if (p1.freq != p2.freq) {
                    return p1.freq - p2.freq;
                }
                return p2.word.compareTo(p1.word);
            }
        };

        public void setup(int k) {
            // initialize your data structure here
            this.k = k;
            pq = new PriorityQueue<WordFreq>(k, pairComparator);
            
        }   

        public void reduce(String key, Iterator<Integer> values) {
            // Write your code here
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            
            WordFreq pair = new WordFreq(key, sum);
            
            if (pq.size() < k) {
                pq.add(pair);
            } else {
                WordFreq peak = pq.peek();
                if (pairComparator.compare(pair, peak) > 0) {
                    pq.poll();
                    pq.add(pair);
                }
            }
            
            
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<WordFreq> pairs = new ArrayList<WordFreq>();
            
            while (!pq.isEmpty()) {
                pairs.add(pq.poll());
            }
            
            // reverse results
            for (int i = pairs.size() - 1; i >= 0; i--) {
                WordFreq pair = pairs.get(i);
                output.collect(pair.word, pair.freq);
            }
        }
    }
}
