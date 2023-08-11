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

        System.out.println(searchMatrix(matrix, 3)); // true
        System.out.println(searchMatrix(matrix, 13));// false
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int topRow = 0;
        int bottomRow = matrix.length - 1;
        int colN = matrix[0].length - 1;

        // find the row
        while (topRow <= bottomRow) { // check with smth simple
            int midRow = topRow + (bottomRow - topRow) / 2;
            if (target < matrix[midRow][0]) { // if target is less than first value of the row, e.g. [5, 8, 10, 11] - target 4
                bottomRow = midRow - 1; // then need to search in higher rows with lesser values
            } else if (target > matrix[midRow][colN]) { // if target is bigger than last value of the row, e.g. [5, 8, 10, 11] - target 12
                topRow = midRow + 1; // then need to search in lower rows with bigger values
            } else {
                break; // if it's somewhere in the middle of the row, then we found it
            }
        }

        if (topRow > bottomRow) return false; // there's might be no match at all

        int theRow = topRow + (bottomRow - topRow) / 2; // we know that it will give us THE row now
        // standard binary search
        int l = 0, r = colN;
        while (l <= r) {
            int midIndex = l + (r - l) / 2;
            int midValue = matrix[theRow][midIndex];
            if (target == midValue) {
                return true;
            }

            if (target > midValue) {
                l = midIndex + 1;
            } else {
                r = midIndex - 1;
            }
        }

        return false;
    }
}
