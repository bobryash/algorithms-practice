package com.leetcode.medium.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * #146. LRU Cache
 * <p>
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }

    // the idea - to have two separate data structures:
    // 1. hashmap - to store cache (key-node),
    // 2. doubly linkedList - to track LRU (values on a left - least recent used(LRU), on a right - most recent(MRU).
    // - during get operation: make a node MRU by removing it from the list and adding it to the right.
    // - during put operation:
    //      - if hashmap contains key (means old value is there), remove old node from list and insert new one as MRU
    //      - if capacity is too big, remove left node (LRU) from list, and also remove that node from hashmap
    //
    //
    Map<Integer, CacheListNode> cache;
    int capacity;

    // dummy nodes

    // helps to find LRU cache (left.next)
    CacheListNode left;

    // helps to find most recently used cache
    CacheListNode right;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        left = new CacheListNode(0, 0);
        right = new CacheListNode(0,0);

        left.next = right;
        right.prev = left;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            // make node the most recent used cache at the moment
            removeFromList(cache.get(key));
            insertAtRight(cache.get(key));

            return cache.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            removeFromList(cache.get(key)); // remove old node from list
        }
        cache.put(key, new CacheListNode(key, value)); // hashmap will always overwrite
        insertAtRight(cache.get(key)); // insert new node in list (as MRU cache)

        if (cache.size() > capacity) {
            // remove LRU cache node from list and hashmap (with help of left dummy pointer)
            CacheListNode lru = left.next;
            cache.remove(lru.key);
            removeFromList(lru);
        }
    }

    // helper methods.
    // work only with pointers, not cache itself (map).
    // check pics to remember add/remove node logic

    // insert node at right (make it MRU, by inserting right most position)
    private void insertAtRight(CacheListNode node) {
        this.right.prev.next = node;
        node.prev = this.right.prev;

        this.right.prev = node;
        node.next = this.right;
    }

    // remove node from the list
    private void removeFromList(CacheListNode node) {
        CacheListNode prev = node.prev;
        CacheListNode next = node.next;

        prev.next = next;
        next.prev = prev;

        // or
        // node.prev.next = node.next;
        // node.next.prev = node.prev;
    }

    class CacheListNode {
        int key;
        int value;

        CacheListNode next;
        CacheListNode prev;

        CacheListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
