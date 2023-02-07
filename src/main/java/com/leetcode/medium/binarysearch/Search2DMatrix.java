package com.leetcode.medium.binarysearch;

/**
 * #74
 * <p>
 * You are given an m x n integer matrix matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [
 * [1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]],
 * target = 3
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [
 * [1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]],
 * target = 13
 * Output: false
 */
public class Search2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        System.out.println(new Search2DMatrix().searchMatrix(matrix, 3)); // true
        System.out.println(new Search2DMatrix().searchMatrix(matrix, 13));// false
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int top = 0;
        int bottom = rows - 1;

        // find a row which contains value first
        while (top <= bottom) {
            int row = top + (bottom - top) / 2;
            if (target > matrix[row][cols - 1]) {
                top = row + 1;
            } else if (target < matrix[row][0]) {
                bottom = row - 1;
            } else {
                break;
            }
        }

        if (top > bottom) {
            return false;
        }

        // then find a value inside this row
        int row = top + (bottom - top) / 2;
        int l = 0;
        int r = cols - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (target > matrix[row][m]) {
                l = m + 1;
            } else if (target < matrix[row][m]) {
                r = m - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
