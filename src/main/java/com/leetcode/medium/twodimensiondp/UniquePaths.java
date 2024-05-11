package com.leetcode.medium.twodimensiondp;

/**
 * #62. Unique Paths
 *
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7)); // 28
        System.out.println(uniquePaths(3, 2)); // 3
    }

    // the idea - 2d dp.
    // number of ways to get to the current square is equal to sum of the number of ways
    // to get to its bottom and down neighbours (it's only ways robot can go).
    // calculate number of steps for each square starting from base case, reach the start and get the result.
    // base case's adjusted row and column will be filled with 1,
    // because right summand of right-most column is always 0,
    // and down summand of bottom row is always 0 too
    // but both still have one way to get to the base case (check pics)
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // fill bottom row with 1
        for (int col = 0; col < n; col++) {
            dp[m - 1][col] = 1;
        }

        // fill right-most column with 1
        for (int row = 0; row < m; row++) {
            dp[row][n - 1] = 1;
        }

        // dp calculate for each position a number of ways we can reach the destination
        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                dp[row][col] = dp[row][col + 1] + dp[row + 1][col];
            }
        }

        return dp[0][0];
    }
}
