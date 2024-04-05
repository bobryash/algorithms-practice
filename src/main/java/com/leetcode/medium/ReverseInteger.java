package com.leetcode.medium;

/**
 * #7. Reverse Integer
 *
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1],
 * then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 *
 * Example 1:
 * Input: x = 123
 * Output: 321
 *
 * Example 2:
 * Input: x = -123
 * Output: -321
 *
 * Example 3:
 * Input: x = 120
 * Output: 21
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(123)); // 321
        System.out.println(reverse(-123)); // -321
        System.out.println(reverse(120)); // 21
        System.out.println(reverse(Integer.MAX_VALUE + 1)); // 0
    }

    // the idea - cut original integer digit by digit from right to left with %
    // (returns leftover from division, or if dividend(8) is less than divisor(10) return dividend:
    // 12 % 10 = 2; 8 % 10 = 8),
    // then append it to result, by making room for digit (result * 10), then by adding digit (result + digit).
    // do overflow check in between:
    // 1. result might be already bigger than (MAX_VALUE - 1digit = 214748364 [7]),
    // in that case it doesn't matter which current digit is, it will overflow.
    // 2. or it might be equal to that value (MAX_VALUE - 1digit = 214748364 [7]), but digit might be more than 7,
    // then it also will overflow.
    // (same for negative)
    //
    // Integer.MAX_VALUE = 2147483647
    // Integer.MIN_VALUE = -2147483648
    public static int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;

            // Check for overflow before appending digit
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            x = x / 10;

            reversed = reversed * 10 + digit;
        }

        return reversed;
    }
}
