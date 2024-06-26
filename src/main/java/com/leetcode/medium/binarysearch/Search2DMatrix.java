package com.leetcode.medium.binarysearch;

/**
 * #74
 * <p>
 * You are given an m x n integer matrix "matrix" with the following two properties:
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
        while (topRow <= bottomRow) {
            int midRow = topRow + (bottomRow - topRow) / 2;
            if (target < matrix[midRow][0]) { // if target is less than first value of the row, e.g. [5, 8, 10, 11] - target 4
                bottomRow = midRow - 1; // then need to search in higher rows with lesser values
            } else if (target > matrix[midRow][colN]) { // if target is bigger than last value of the row, e.g. [5, 8, 10, 11] - target 12
                topRow = midRow + 1; // then need to search in lower rows with bigger values
            } else {
                break; // if it's somewhere in the current row, then we found it
            }
        }

        // all rows are checked - no match
        if (topRow > bottomRow) return false;

        // we know that it will give us THE row now (same as last midRow)
        int theRow = topRow + (bottomRow - topRow) / 2;
        // standard binary search
        int left = 0, right = colN;
        // <= in bin search is for keep searching while there's space = when target is in the
        // one of the extremes: target 4-[1,2,3,4], mid = 3 + (3-3)/2 = i3 v4
        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            int midValue = matrix[theRow][midIndex];
            if (target == midValue) {
                return true;
            }

            if (target > midValue) {
                left = midIndex + 1;
            } else {
                right = midIndex - 1;
            }
        }

        return false;
    }
}
