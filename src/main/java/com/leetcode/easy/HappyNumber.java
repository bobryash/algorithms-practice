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
    // There will always be a cycle while you start summing squares of a number’s digits.
    // Happy number’s cycle will start with 1, unhappy number’s with not 1.
    //
    //Examples:
    // Number 2 is not happy, because cycle in its square loop starts not with 1:
    //2 4 8 64 36+16=52 25+4=29 4+81=85(!) 64+25=89 64+81=145 1+16+25=42 8+4=12 1+4=5 25 4+25=29 4+81=85(!)
    //
    // Number 19 is happy because its square cycle starts with 1:
    //19 1+81=82 64+4=68 36+64=100 1+0+0=1 1 1..
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do { // have to move them at least once before comparison
            slow = compute(slow);
            fast = compute(compute(fast));
        } while (slow != fast);

        return slow == 1;
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
