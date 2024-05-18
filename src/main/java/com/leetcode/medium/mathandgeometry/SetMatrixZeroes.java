package com.leetcode.medium.mathandgeometry;

import java.util.Arrays;

/**
 * #73. Set Matrix Zeroes
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 *
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        // [[1,0,1],
        //  [0,0,0],
        //  [1,0,1]]
        int[][] ex1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(ex1);
        System.out.println(Arrays.deepToString(ex1));

        // [[0,0,0,0],
        //  [0,4,5,0],
        //  [0,3,1,0]]
        int[][] ex2 = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(ex2);
        System.out.println(Arrays.deepToString(ex2));

        // [[0,0,0,0,0],
        //  [0,0,0,0,0],
        //  [2147483647,2,-9,-6,0]]
        int[][] ex3 = new int[][]{
                {-4, -2147483648, 6, -7, 0},
                {-8, 6, -8, -6, 0},
                {2147483647, 2, -9, -6, -10}
        };
        setZeroes(ex3);
        System.out.println(Arrays.deepToString(ex3));
    }

    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRow = false;

        // set markers in first row and first columns
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;

                    if (row > 0) {
                        matrix[row][0] = 0;
                    } else {
                        firstRow = true;
                    }
                }
            }
        }

        // set zeroes outside marker row and column
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        // set zeroes in marker row and column

        // first column
        // important to do it before first row, because firstRow can falsely set 0 for this column marker.
        // (remember that firstRow var is an outside value of a first column marker)
        if (matrix[0][0] == 0) {
            for (int row = 0; row < rows; row++) {
                matrix[row][0] = 0;
            }
        }

        // first row
        if (firstRow) {
            for (int col = 0; col < cols; col++) {
                matrix[0][col] = 0;
            }
        }
    }
}
