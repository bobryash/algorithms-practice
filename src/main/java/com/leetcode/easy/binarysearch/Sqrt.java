package com.leetcode.easy.binarysearch;

/**
 * #69. Sqrt(x)
 * <p>
 * Sqrt(x)
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 * <p>
 * You must not use any built-in exponent function or operator.
 * <p>
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 * Example 2:
 * <p>
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 * <p>
 * Time complexity: O(logn)
 * Space complexity: O(1)
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(Sqrt.mySqrt(16)); // 4
        System.out.println(Sqrt.mySqrt(8)); // 2
        System.out.println(Sqrt.mySqrt(1)); // 1
    }

    // the idea - square root value of x is somewhere in 0...x
    // use binary search to find it
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;

        while (left <= right) {
            // since we use integer division here...
            int mid = left + (right - left) / 2;
            // use long here so integer won't overflow if there are big values
            long product = (long) mid * mid;

            if (product < x) {
                left = mid + 1;
            } else if (product > x) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        // ...we might've rounded down the 'right' value - nearest largest value to square root.
        // so return it, if we got here
        return right;
    }

    public static int mySqrt2(int x) {
        if (x == 0) return x;

        int left = 1;
        int right = x; // 8

        /*
            If mid equals x / mid, it means we have found the square root, so we return mid.
            If mid is less than x / mid, it means the current mid is too small, and we update the left boundary to mid + 1.
            If mid is greater than x / mid, it means the current mid is too large, and we update the right boundary to mid - 1.
         */
        while (left <= right) { // 1 <= 16
            int mid = left + (right - left) / 2; // 8

            if (x / mid == mid) { // 16/8 == 8 -> 2 != 8
                return mid;
            } else if (mid < x / mid) { // 8 < 16/8 -> 8 < 2 false
                left = mid + 1;
            } else { // true
                right = mid - 1;
            }
        }

        return right;
    }
}
