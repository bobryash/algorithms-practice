package com.leetcode.easy.binarysearch;

import java.util.Arrays;
import java.util.List;

/**
 * #278
 * <p>
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all
 * the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {

    public static void main(String[] args) {
        int firstBadVersion = firstBadVersion(5);
        System.out.println(firstBadVersion);
    }

    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left)/2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean isBadVersion(int v) {
        List<Integer> bad = Arrays.asList(4, 5);
        return bad.contains(v);
    }
}
