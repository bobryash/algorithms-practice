package com.leetcode.easy;

/**
 * #125. Valid Palindrome
 * <p>
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 * <p>
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 * <p>
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        test("A man, a plan, a canal: Panama"); // true
        test("race a car"); // false
        test(" "); // true
    }

    public static boolean isPalindromeExtraMemory(String s) {
        StringBuilder sb = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }

        return sb.toString().toLowerCase().equals(sb.reverse().toString().toLowerCase());
    }

    public static boolean isPalindromeConstantMemory(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();

        while (left < right) {
            while (!isAlphaNumerical(chars[left]) && left < right) {
                left++;
            }
            while (!isAlphaNumerical(chars[right]) && left < right) {
                right--;
            }

            if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }

    private static boolean isAlphaNumerical(Character c) {
        return (c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z') ||
                (c >= '0' && c <= '9');
    }

    private static void test(String s) {
        System.out.println(isPalindromeExtraMemory(s));
        System.out.println(isPalindromeConstantMemory(s));
        System.out.println("---");
    }
}
