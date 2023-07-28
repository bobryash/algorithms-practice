package com.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * #128. Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // 4
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // 9
    }

    // the idea - find first value in a sequence, then try to find next values starting from it.
    // how to find first value n in sequence - this means that array doesn't have value n - 1:
    // [100,4,200,1,3,2] - there is no 0 for 1, no 99 for 100, no 199 for 200 - they all start of their own sequences
    public static int longestConsecutive(int[] nums) {
        // easier to check set instead of array
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int longest = 0;

        for (int n : nums) {
            if (!set.contains(n - 1)) { // n is a start of a sequence
                int seqLength = 0;
                while (set.contains(n + seqLength)) { // how long is a sequence?
                    seqLength += 1;
                }
                longest = Math.max(seqLength, longest);
            }
        }

        return longest;
    }
}
