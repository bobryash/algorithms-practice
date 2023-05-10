package com.leetcode.easy;

/**
 * #14. Longest Common Prefix
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // "fl"
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"})); // ""
        System.out.println(longestCommonPrefix(new String[]{"flow", "flower", "flight"})); // "fl" ? ["c","acc","ccc"]
        System.out.println(longestCommonPrefix(new String[]{"c", "acc", "ccc"})); //
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0]; // изначально сравниваем только с первой строкой
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // indexOf == 0 means there's a full match starting from the beginning (but potentially could be anywhere in the string)
                prefix = prefix.substring(0, prefix.length() - 1);
                // if() block below is not necessary because (prefix.length - 1) will reach 0 eventually
                // and substring(0, 0) will return empty string "", and indexOf(substring(0, 0)) == 0 will be true (while loop will be terminated).
                // (string.indexOf(someString.substring(0, 0)) - true for every string)
//                if (prefix.isEmpty()) {
//                    return "";
//                }
            }
        }
        return prefix;
    }
}
