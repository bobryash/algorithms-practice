package com.leetcode.medium.graphs;

import java.util.Arrays;

/**
 * #130. Surrounded Regions
 *
 * Given an m x n matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example 1:
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Notice that an 'O' should not be flipped if:
 * - It is on the border, or
 * - It is adjacent to an 'O' that should not be flipped.
 * The bottom 'O' is on the border, so it is not flipped.
 * The other three 'O' form a surrounded region, so they are flipped.
 *
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] example1 = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(example1);
        // [[X, X, X, X], [X, X, X, X], [X, X, X, X], [X, 0, X, X]]
        System.out.println(Arrays.deepToString(example1));


        char[][] example2 = new char[][]{
                {'X'}
        };
        solve(example2);
        // [[X]]
        System.out.println(Arrays.deepToString(example2));
    }

    // the idea is similar to #417. Pacific Atlantic Water Flow:
    // you start from the borders, up-down left-right,
    // then find O in them - those square can not be surrounded, because they're
    // adjusted to the borders. mark them with temp 'T' valut and launch dfs to adjusted squares,
    // trying to find adjusted O's, to also mark them 'T'. such islands can not be surrounded
    // then go through each square and mark every leftover O as X, meaning they can be surrounded,
    // and flip back all 'T's into O.
    // N.B. - don't confuse O with 0
    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int c = 0; c < cols; c++) {
            dfs(board, 0, c);
            dfs(board, rows - 1, c);
        }

        for (int r = 0; r < rows; r++) {
            dfs(board, r, 0);
            dfs(board, r, cols - 1);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] board, int r, int c) {
        int rows = board.length;
        int cols = board[0].length;

        if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') {
            return;
        }

        board[r][c] = 'T';

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}
