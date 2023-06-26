package com.leetcode.easy;

import java.util.*;

/**
 * #350. Intersection of Two Arrays II
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * <p>
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 */
public class IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        // count integers frequency in nums1
        Map<Integer, Integer> frequency1map = new HashMap<>();
        for (int i: nums1) {
            frequency1map.put(i, frequency1map.getOrDefault(i, 0) + 1);
        }

        // find intersections with nums2
        List<Integer> intersections = new ArrayList<>();
        for (int i: nums2) {
            if (frequency1map.containsKey(i) && frequency1map.get(i) > 0) {
                intersections.add(i);
                frequency1map.put(i, frequency1map.get(i) - 1); // to avoid duplicates: [3,3] [3,3,3]
            }
        }

        // convert list to result array
        int[] result = new int[intersections.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersections.get(i);
        }

        return result;
    }
}
