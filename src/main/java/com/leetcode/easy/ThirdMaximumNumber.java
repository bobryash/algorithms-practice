package com.leetcode.easy;

import java.util.PriorityQueue;

/**
 * #414. Third Maximum Number
 *
 * Given an integer array nums, return the third distinct maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 *
 *
 *
 * Example 1:
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 *
 * Example 2:
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 *
 * Example 3:
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 */
public class ThirdMaximumNumber {

    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{3, 2, 1})); // 1
        System.out.println(thirdMax(new int[]{1, 2})); // 2
        System.out.println(thirdMax(new int[]{2, 2, 3, 1})); // 1
    }

    public static int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i: nums) {
            if (pq.contains(i)) {
                continue;
            }
            pq.offer(i);
        }

        if (pq.size() == 1) return pq.peek();
        if (pq.size() == 2) {
            pq.poll();
            return pq.peek();
        }

        while (pq.size() > 3) {
            pq.poll();
        }

        return pq.peek();
    }
}
