package com.leetcode.medium.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * #120
 * <p>
 * Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below. More formally,
 * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 */
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(List.of(2));
        triangle.add(List.of(3, 4));
        triangle.add(List.of(6, 5, 4));
        triangle.add(List.of(4, 1, 8, 3));

        int result = new Triangle().minimumTotal(triangle);

        System.out.println(result); // 11
    }


    // The solution uses an array dp of size n + 1 to store the minimum path sum from the bottom to the current row.
    // The solution starts from the bottom row and works its way up to the top. For each cell in the current row,
    // the minimum path sum is equal to the value of the cell plus the minimum of the minimum path sum of the two cells
    // in the next row.
    //
    // Finally, the solution returns the minimum path sum in the first element of dp, which is the minimum path sum
    // from the top to the bottom of the triangle.
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];

        // 0  0  0 0 0
        // 4  1  8 3 0
        // 7  6  7 3 0
        // 9  10 7 3 0
        // 11 10 7 3 0
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0]; // 11
    }
}
