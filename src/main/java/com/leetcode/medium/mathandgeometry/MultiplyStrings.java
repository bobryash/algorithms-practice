package com.leetcode.medium.mathandgeometry;

/**
 * #43. Multiply Strings
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(multiply("2", "3")); // "6"
        System.out.println(multiply("123", "456")); // "56088"
        System.out.println(multiply("5", "0")); // "0"
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";

        // easier to build the answer with integer array, then turn it into a string
        // result size will be equal or less then sum of nums lengths
        int[] result = new int[num1.length() + num2.length()];

        num1 = reverseString(num1);
        num2 = reverseString(num2);

        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int product = Character.getNumericValue(num1.charAt(i)) *
                        Character.getNumericValue(num2.charAt(j)); // e.g., 9 * 9 = 81
                // += cause there already might've been a carry in i+j and i+j+1
                result[i + j] += product; // e.g., 9*9 = 81 -> [81, 0, 0]
                result[i + j + 1] += result[i + j] / 10;  // [81, 8, 0]
                result[i + j] = result[i + j] % 10; // [1, 8, 0]
            }
        }

        reverseArray(result);

        // e.g., 10 * 10
        // resLength = [length1 + length2] = [2 + 2] = [4]
        // reverse strings: 01 * 01
        // fill result = [0010]
        // reverse result: [0100] - might be leading zeroes
        int startIndex = 0;
        while (startIndex < result.length) {
            if (result[startIndex] != 0) {
                break;
            }
            startIndex++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < result.length; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }

    private static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    private static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
