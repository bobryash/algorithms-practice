package com.leetcode.medium.mathandgeometry;

import java.util.Arrays;

/**
 * #48. Rotate Image
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 *
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] ex1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(ex1);
        System.out.println(Arrays.deepToString(ex1)); // [[7,4,1],[8,5,2],[9,6,3]]

        int[][] ex2 = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(ex2);
        System.out.println(Arrays.deepToString(ex2)); // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    }

    public static void rotate(int[][] matrix) {
        // Initialize pointers to the current layer being processed
        int left = 0, right = matrix.length - 1;

        // Process layers from the outermost to the innermost
        while (left < right) {
            int top = left, bottom = right;

            // For each element in the current layer, perform the four-way swap
            for (int i = 0; i < right - left; i++) {
                // Store the top-left element
                int topLeft = matrix[top][left + i];

                // Move bottom-left to top-left
                matrix[top][left + i] = matrix[bottom - i][left];

                // Move bottom-right to bottom-left
                matrix[bottom - i][left] = matrix[bottom][right - i];

                // Move top-right to bottom-right
                matrix[bottom][right - i] = matrix[top + i][right];

                // Move top-left (stored in topLeft) to top-right
                matrix[top + i][right] = topLeft;
            }

            // Move to the next inner layer
            left++;
            right--;
        }
    }
}
