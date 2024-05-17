package com.leetcode.medium.mathandgeometry;

import java.util.ArrayList;
import java.util.List;

/**
 * #54. Spiral Matrix
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        // [1,2,3,6,9,8,7,4,5]
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));

        // [1,2,3,4,8,12,11,10,9,5,6,7]
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));

        // edge cases
        // one row
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}})); // [1, 2, 3]
        // one column
        System.out.println(spiralOrder(new int[][]{{1}, {2}, {3}})); // [1, 2, 3]

        // bonus: (one row / one column) input
        System.out.println(spiralOrder(new int[][]{{0}})); // [0]
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1; // number of rows
        int left = 0;
        int right = matrix[0].length - 1; // number of columns

        List<Integer> result = new ArrayList<>();
        while (top <= bottom && left <= right) {
            // top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // for the edge case, when we have one row input [[1, 2, 3]]
            if (top <= bottom) { // are there any rows left?
                // bottom row
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // for the edge case, when we have one column input [[1], [2], [3]]
            if (left <= right) { // are there any columns left?
                // left column
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
