package com.leetcode.medium;

import java.util.*;

/**
 * #347. Top K Frequent Elements
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));
    }

    // the idea - one number can't appear more times, than array's length
    // so count all nums frequencies, and create array with indexes-buckets for each frequency values
    // index 1 - 1 appearance, 2 - 2, etc..
    // indexes hold lists with numbers which appeared that times
    // 1 1 1 2 2 2 3: index 0 - null(always be), index 1 - [3], index 2 - null, index 3 - [1, 2] ... index 6 - null
    public static int[] topKFrequent(int[] nums, int k) {
        // count each num frequency (num -> frequency)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i : nums) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }

        // create bucket for each frequency instance
        // freq -> [numbers with same freq]
        List<Integer>[] buckets = new ArrayList[nums.length + 1]; // +1 because array is 0-based, but it will be always at least 1 count, and index == number's count. consider case with 1 element in an input array
        for (int num : frequencyMap.keySet()) {
            Integer freqIndex = frequencyMap.get(num);
            if (buckets[freqIndex] == null) {
                buckets[freqIndex] = new ArrayList<>();
            }
            buckets[freqIndex].add(num);
        }

        // extract top k
        int[] result = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int j : buckets[i]) {
                    result[index] = j;
                    index++;
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
