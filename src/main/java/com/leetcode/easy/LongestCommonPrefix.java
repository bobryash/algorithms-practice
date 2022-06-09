package com.leetcode.easy;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        //System.out.println("flower".indexOf("flow")); // 0
        //System.out.println("flow".indexOf("flower")); // -1
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // "fl"
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"})); // ""
        System.out.println(longestCommonPrefix(new String[]{"flow", "flower", "flight"})); // "fl" ?
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0]; // изначально сравниваем только с первой строкой
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
