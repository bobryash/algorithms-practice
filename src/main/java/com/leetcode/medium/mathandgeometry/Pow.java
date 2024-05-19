package com.leetcode.medium.mathandgeometry;

/**
 * #50. Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */
public class Pow {

    public static void main(String[] args) {
        System.out.println(myPow(2, 10)); // 1024.00000
        System.out.println(myPow(2.1, 3)); // 9.26100
        System.out.println(myPow(2, -2)); // 0.25000
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    // the idea - divide and conquer.
    // e.g. for 2^8 instead of calculating 2*2*2...*2 eight times,
    // try to calculate 2^(8/2=4) first, then 2^(4/2=2), then down to base case - 2^(2/2=1) = 2.
    // it reduces time complexity to logN.
    // edge cases:
    // - if exponent n is negative, then x = 1/x, and make n positive by -n
    // - if exponent n is negative AND it equals to Integer.MIN_VALUE: always convert n to long to avoid overflow
    // - if exponent is odd, then you can't divide long evenly like n=5, 5/2 = 2,
    //      so you will need to additionally multiply result by x
    public static double myPow(double x, int n) {
        if (n == 0) return 1;

        // we're doing this because if we do -n and n=Integer.MIN_VALUE then
        // it'll become a value which is greater than the max value of Integer.MAX_VALUE
        // Integer.MIN_VALUE: -2147483648 (if invert sign - then overflow: +2147483647 is a limit for Integer)
        // Integer.MAX_VALUE: 2147483647
        long N = n; // Convert n to long to handle Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N; // make it positive
        }

        return powRecursive(x, N);
    }

    // e.g. 2^8
    private static double powRecursive(double x, long n) {
        if (n == 0) return 1.0;
        double half = powRecursive(x, n / 2); // find 2^4

        if (n % 2 == 0) {
            return half * half; // then just multiply it by itself: 2^4 * 2^4 = 2^8
        } else {
            return x * half * half; // case when exponent is odd, e.g. 5. then 2 * 2^2 * 2^2
        }
    }
}
