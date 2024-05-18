package com.leetcode.easy.mathandgeometry;

/**
 * #415. Add Strings
 *
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 *
 * Example 1:
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 *
 * Example 2:
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 *
 * Example 3:
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("11", "123")); // 134
        System.out.println(addStrings("456", "77")); // 533
        System.out.println(addStrings("0", "0")); // 0
    }

    public static String addStrings(String num1, String num2) {
        int num1c = num1.length() - 1;
        int num2c = num2.length() - 1;

        StringBuilder result = new StringBuilder();
        int carry = 0;

        while (num1c >= 0 || num2c >= 0) {
            int i1 = num1c >= 0 ? Character.getNumericValue(num1.charAt(num1c)) : 0;
            int i2 = num2c >= 0 ? Character.getNumericValue(num2.charAt(num2c)) : 0;

            int sum = i1 + i2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);

            num1c--;
            num2c--;
        }

        if (carry != 0) result.insert(0, carry);

        return result.toString();
    }
}
