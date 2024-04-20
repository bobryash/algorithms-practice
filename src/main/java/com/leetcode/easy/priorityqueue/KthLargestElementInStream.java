package com.leetcode.easy.priorityqueue;

import java.util.PriorityQueue;

/**
 * #703. Kth Largest Element in a Stream
 *
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns
 * the element representing the kth largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 */
public class KthLargestElementInStream {

    public static void main(String[] args) {
        KthLargestElementInStream solution = new KthLargestElementInStream(3, new int[]{4, 5, 8, 2});
        System.out.println(solution.add(3)); // 4
        System.out.println(solution.add(5)); // 5
        System.out.println(solution.add(10)); // 5
        System.out.println(solution.add(9)); // 8
        System.out.println(solution.add(4)); // 8
    }

    private PriorityQueue<Integer> pq = new PriorityQueue<>();
    private int k;

    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        for (int n: nums) {
            add(n);
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }

        return pq.peek();
    }
}
