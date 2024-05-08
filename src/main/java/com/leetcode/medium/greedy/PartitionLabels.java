package com.leetcode.medium.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #763. Partition Labels
 *
 * You are given a string s. We want to partition the string into
 * as many parts as possible so that each letter appears in at most one part.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 * Example 1:
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 *
 *
 * Example 2:
 * Input: s = "eccbbbbdec"
 * Output: [10]
 */
public class PartitionLabels {

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij")); // [9, 7, 8]
        System.out.println(partitionLabels("eccbbbbdec")); // [10]
    }

    // the goal - to part a string into partitions which contain only unique letters.
    // the idea - letter can occur multiple times in a string, so the size of a partition
    // for this letter to be in would be from its first and last occurrence in a string.
    // keep track of this range (start-end) starting from the first letter,
    // and if you find a letter which occurs even further than current one - update end,
    // because we want to have a contiguous partition.
    // once your loop's index reaches the current end value, that means that we have a complete
    // partition - add size to result and update start (i + 1).
    // (N.B.: why is greedy -  the algorithm iterates through the characters of the string and
    // greedily chooses the largest possible partition at each step)
    public static List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();

        // to quickly find last occurrence of a char in string
        Map<Character, Integer> lastIndexesOf = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndexesOf.put(chars[i], i);
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lastIndexOfC = lastIndexesOf.get(s.charAt(i));
            if (lastIndexOfC > end) {
                end = lastIndexOfC;
            }

            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }

    // my OG solution
    public static List<Integer> partitionLabels2(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> lastIndexesOf = new HashMap<>();
        // do O(n) search for each letter, not good
        for (char c: chars) {
            lastIndexesOf.put(c, s.lastIndexOf(c));
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int lastIndexOfC = lastIndexesOf.get(s.charAt(i));
            if (lastIndexOfC > end) {
                end = lastIndexOfC;
            }

            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }
}
