package com.leetcode.easy;

/**
 * #171. Excel Sheet Column Number
 * Given a string columnTitle that represents the column title as
 * appears in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * <p>
 * Example 1:
 * Input: columnTitle = "A"
 * Output: 1
 * <p>
 * Example 2:
 * Input: columnTitle = "AB"
 * Output: 28
 * <p>
 * Example 3:
 * Input: columnTitle = "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        System.out.println(titleToNumber("A")); // 1
        System.out.println(titleToNumber("AB")); // 28
        System.out.println(titleToNumber("ZY")); // 701
    }

    public static int titleToNumber(String columnTitle) {
        int result = 0;

        for (char ch : columnTitle.toCharArray()) {
            int digitValue = ch - 'A' + 1; // 'A' == 65 in Unicode
            result = result * 26 + digitValue; //  simulating the base-26 positional value system
        }

        return result;
    }
}
