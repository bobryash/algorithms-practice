package com.leetcode.medium;

import java.util.*;

/**
 * #49. Group Anagrams
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        List<List<String>> result;

        result = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(result);

        result = groupAnagrams(new String[]{""});
        System.out.println(result);

        result = groupAnagrams(new String[]{"a"});
        System.out.println(result);
    }

    // the idea - count chars for each string, based on this count create key-list pairs, add string to list by key
    // chars are kind of sorted for each string, because we use char[26] array
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> counts = new HashMap<>();

        for (String s : strs) {
            char[] charsCount = new char[26];
            for (char c : s.toCharArray()) {
                charsCount[c - 'a']++;
            }

            String key = String.valueOf(charsCount);
            if (!counts.containsKey(key)) {
                counts.put(key, new ArrayList<>());
            }
            counts.get(key).add(s);
        }

        return new ArrayList<>(counts.values());
    }
}
