package com.leetcode.medium.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * #179. Largest Number
 *
 * Given a list of non-negative integers nums,
 * arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 *
 * Example 1:
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2:
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 */
public class LargestNumber {

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{10, 2}));
        //System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }


    public static String largestNumber(int[] nums) {
        List<String> stringNums = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> {
                    String s1s2 = s1 + s2;
                    String s2s1 = s2 + s1;

                    return s2s1.compareTo(s1s2);
                }).collect(Collectors.toList());

        // edge case when array consists ONLY from 0s
        // then we need return only single "0", not like "0000"
        if ('0' == stringNums.get(0).charAt(0)) {
            return "0";
        }

        return String.join("", stringNums);
    }

    // the idea - you need to put numbers with bigger digits first,
    // but how to figure which one has it?
    // 30 vs 3 - 30 > 3, but 303 < 330.
    // answer - convert them into strings, concatenate and compare
    // "30" + "3", "3" + "30" -> "303" < "330", so order should be ["3", "30"]
    public static String largestNumber2(int[] nums) {
        String[] stringNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stringNums[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(stringNums, (s1, s2) -> {
            String s1s1 = s1 + s2;
            String s2s1 = s2 + s1;

            return (s2 + s1).compareTo(s1 + s2);
        });

        // edge case when array consists ONLY from 0s
        // then we need return only single "0", not like "0000"
        if (stringNums[0].charAt(0) == '0') {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String stringNum : stringNums) {
            sb.append(stringNum);
        }

        return sb.toString();
    }
}
