package com.leetcode.medium.bfsdfs;

import java.util.Arrays;

/**
 * #994
 * <p>
 * You are given an m X n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 */
public class RottingOranges {

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int result = new RottingOranges().orangesRotting(grid);

        System.out.println(result); // 4
        Arrays.stream(grid).forEach(a -> {
            System.out.println(Arrays.toString(a));
        });
        // [2, 3, 4]
        // [3, 4, 0]
        // [0, 5, 6]
    }

    // DFS
    public int orangesRotting(int[][] grid) {
        // First check if the input is invalid.
        if (grid == null || grid.length == 0) {
            return -1;
        }

        // Then, iterate over every cell in the matrix with rotAdjacent if the cell is rotten.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    rotAdjacent(grid, i, j, 2);
                }
            }
        }

        // After we finish with all the rotAdjacent, we return and check if there's
        // any fresh cells left, in which case we fail.
        int minutes = 2;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 1) {
                    return -1;
                }
                minutes = Math.max(minutes, cell);
            }
        }

        // Otherwise, we get the largest value from the grid, which represents the timestep at which the final fresh cell
        // was rotted, remove the offset of 2, and return.
        return minutes - 2;
    }

    private void rotAdjacent(int[][] grid, int i, int j, int minutes) {
        // In rotAdjacent there has to be bounds checking:
        if (i < 0 || i >= grid.length // 1) check if we are at the left or right edge
                || j < 0 || j >= grid[0].length // 2) check if we are at the top or bottom edge
                || grid[i][j] == 0 // 3) check if the cell is empty
                || (1 < grid[i][j] && grid[i][j] < minutes) // 4) check if this cell has already been touched by another
                                                // depth-first search using rotAdjacent that hit
                                                // it faster than the original rot we are recurring from currently.
                                                // 1 < 2 < 4 - идет 4 минута, а 2 значит что он протух раньше, значит идем дальше
        ) {
            return;
        }

        // Otherwise, we store minutes, which represents the current time-step in the cell.
        // Minutes at the first time step is 2 to offset that values 0 and 1 are reserved to indicate empty and fresh cells.
        grid[i][j] = minutes;

        // Then we recursively invoke rotAdjacent on all adjacent cells, left, right, top, and bottom.
        rotAdjacent(grid, i - 1, j, minutes + 1);
        rotAdjacent(grid, i + 1, j, minutes + 1);
        rotAdjacent(grid, i, j - 1, minutes + 1);
        rotAdjacent(grid, i, j + 1, minutes + 1);

        // By starting recursive rotting from each rotten cell, we can see if it is possible to spread adjacently to
        // all fresh cells.
    }
}
