import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    public LRUCache(int capacity) {
        // true: for access order
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {

        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {

        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}


class LRUCache2 {
    // use Double linked list & hashmap
    // Double linked list: O(1) to add/remove node from head/tail
    // Hashmap: O(1) to get key
    // dummy node: head, tail

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        DLinkedNode() {}

        DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private Map<Integer, DLinkedNode> map = new HashMap<>();
    private int size, capacity;
    private DLinkedNode head, tail;

    private void moveToTail(DLinkedNode node) {
        removeNode(node);
        addToTail(node);
    }

    private void addToTail(DLinkedNode node) {
        node.prev = tail.prev;
        node.next = tail;

        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache2(int capacity) {
        // head: the eldest node
        // tail: the newest node
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            // else, move node to the tail
            moveToTail(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        // if found in 'map', move found node to the tail
        // else, put the 'new' node to tail & remove the head node

        DLinkedNode node = map.get(key);

        if (node == null) {
            // add new node to tail & remove head node (if size > capacity)
            DLinkedNode newNode = new DLinkedNode(key, value);
            addToTail(newNode);
            map.put(key, newNode);
            size ++;

            if (size > capacity) {
                // 1. remove key in 'map'
                // 2. remove node
                // if 2->1, then we will delete wrong key in map
                map.remove(head.next.key);
                size --;
                removeNode(head.next);
            }
        } else {
            // move found node to tail
            moveToTail(node);
            // update new value
            node.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1 [1, 2]->[2, 1]
        cache.put(3, 3);    // evicts key 2, [1, 3]
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1, [3, 4]
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
