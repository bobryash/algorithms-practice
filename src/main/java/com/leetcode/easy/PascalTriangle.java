package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #118. Pascal's Triangle
 * <p>
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * <p>
 *          1
 *        1   1
 *      1   2   1
 *    1   3   3   1
 *  1   4   6   4   1
 * <p>
 * Example 1:
 * <p>
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: numRows = 1
 * Output: [[1]]
 *
 */
public class PascalTriangle {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generate(5).toArray()));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows <= 0) {
            return triangle;
        }

        // Create the first row with a single element 1
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        // Compute the elements in between based on the previous row
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> newRow = new ArrayList<>();

            // First element of the current row is always 1
            newRow.add(1);
            for (int j = 1; j < prevRow.size(); j++) {
                newRow.add(prevRow.get(j) + prevRow.get(j - 1));
            }
            // Last element of the current row is always 1
            newRow.add(1);

            // Add the current row to the triangle
            triangle.add(newRow);
        }

        return triangle;
    }
}
