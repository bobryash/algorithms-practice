package com.leetcode.easy;

/**
 * #202. Happy Number
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * <p>
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * Example 2:
 * Input: n = 2
 * Output: false
 */
public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(isHappy(19)); // true
        System.out.println(isHappy(2)); // false
        System.out.println(isHappy(10)); // true - tricky case
    }

    // idea is similar to LinkedListCycle solution - use Floyd's tortoise and hare algorithm (fast and slow pointers)
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do { // safety for tricky case
            slow = compute(slow);
            fast = compute(compute(fast));

            if (slow == 1) {
                return true;
            }
        } while (slow != fast);

        return false;
    }

    private static int compute(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum = sum + digit * digit;
            n = n / 10;
        }
        return sum;
    }
}
