package com.leetcode.medium.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * #417. Pacific Atlantic Water Flow
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges,
 * and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where heights[r][c]
 * represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north,
 * south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that
 * rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 * Example 1
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *        [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 *
 * Example 2:
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        System.out.println(pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        })); // [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        System.out.println(pacificAtlantic(new int[][]{
                {1}
        })); // [[0, 0]]

        System.out.println(pacificAtlantic(new int[][] {
                {19, 16, 16, 12, 14, 0, 17, 11, 2, 0, 18, 9, 13, 16, 8, 8, 8, 13, 17, 9, 16, 9, 4, 7, 1, 19, 10, 7, 0, 15},
                {0, 11, 4, 14, 9, 0, 6, 13, 16, 5, 19, 9, 4, 5, 4, 12, 0, 13, 0, 7, 9, 12, 13, 15, 3, 7, 4, 9, 15, 1},
                {13, 14, 12, 12, 12, 16, 6, 15, 13, 1, 8, 9, 11, 14, 14, 10, 19, 11, 10, 0, 5, 18, 4, 12, 7, 13, 17, 15, 18, 1},
                {16, 14, 19, 5, 8, 2, 11, 17, 7, 1, 4, 6, 5, 18, 7, 15, 6, 19, 18, 12, 1, 14, 2, 2, 0, 9, 15, 14, 13, 19},
                {17, 4, 12, 9, 12, 10, 12, 10, 4, 5, 12, 7, 2, 12, 18, 10, 10, 8, 6, 1, 5, 13, 10, 3, 5, 3, 11, 4, 8, 11},
                {8, 19, 18, 9, 6, 2, 7, 3, 19, 6, 0, 17, 9, 12, 11, 1, 15, 11, 18, 1, 8, 11, 1, 11, 16, 7, 8, 17, 15, 0},
                {7, 0, 5, 11, 1, 7, 12, 18, 12, 1, 5, 2, 11, 7, 18, 12, 0, 11, 9, 18, 5, 2, 3, 1, 1, 1, 8, 14, 19, 5},
                {2, 14, 2, 16, 17, 19, 10, 16, 1, 16, 16, 3, 19, 12, 13, 17, 19, 12, 16, 10, 16, 8, 16, 12, 6, 12, 13, 17, 9, 12},
                {8, 1, 10, 5, 7, 0, 15, 19, 8, 15, 4, 12, 18, 18, 13, 11, 5, 2, 8, 3, 15, 4, 3, 7, 7, 14, 15, 11, 6, 16},
                {0, 5, 13, 19, 1, 1, 2, 4, 16, 2, 16, 9, 15, 15, 10, 10, 18, 11, 17, 1, 5, 14, 5, 19, 7, 0, 13, 7, 13, 7},
                {11, 6, 16, 12, 4, 2, 9, 11, 17, 19, 12, 10, 6, 16, 17, 5, 1, 18, 19, 7, 15, 1, 14, 0, 3, 19, 7, 3, 4, 13},
                {4, 11, 8, 10, 10, 19, 7, 18, 4, 2, 2, 14, 6, 9, 18, 14, 2, 16, 5, 3, 19, 17, 4, 3, 7, 1, 12, 2, 4, 3},
                {14, 16, 3, 11, 13, 13, 6, 16, 18, 0, 17, 19, 4, 1, 14, 12, 4, 17, 5, 19, 8, 13, 15, 3, 15, 4, 1, 14, 12, 10},
                {13, 2, 12, 2, 16, 12, 19, 10, 19, 12, 19, 14, 12, 17, 16, 3, 13, 7, 3, 15, 16, 7, 10, 15, 14, 10, 6, 5, 2, 18}
        })); // //[[0,29],[1,28],[2,28],[12,0],[12,1],[13,0]]
    }

    // the idea - start from coasts, then go as high as you can.
    // create maps for pacific and atlantic oceans (boolean[][] - can square's water reach the ocean or not)
    // then compare these two maps, find squares which both has true in them
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] canReachPacific = new boolean[rows][cols];
        boolean[][] canReachAtlantic = new boolean[rows][cols];


        // pacific if up and left coasts
        // atlantic is down and right coasts

        for (int i = 0; i < cols; i++) { // going through each column of..
            dfs(heights, canReachPacific, 0, i, Integer.MIN_VALUE); // ..first row (pacific)
            dfs(heights, canReachAtlantic, rows - 1, i, Integer.MIN_VALUE); // ..last row (atlantic)
        }

        for (int j = 0; j < rows; j++) { // going through each row of..
            dfs(heights, canReachPacific, j, 0, Integer.MIN_VALUE); // ..first column (pacific)
            dfs(heights, canReachAtlantic, j, cols - 1, Integer.MIN_VALUE); // ..last column (atlantic)
        }

        // find matches - squares from which water can reach both pacific and atlantic
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if both true. pls don't use == operator (they both can be false, which is also true)
                if (canReachPacific[i][j] && canReachAtlantic[i][j]) {
                    List<Integer> canReachBoth = new ArrayList<>();
                    canReachBoth.add(i);
                    canReachBoth.add(j);
                    result.add(canReachBoth);
                }
            }
        }

        return result;
    }

    private static void dfs(int[][] heights, boolean[][] visit, int row, int col, int prevValue) {
        int rows = heights.length;
        int cols = heights[0].length;

        // check for if out of bounds, if square was already visited,
        // and super important check for height -
        // current height should be equal or bigger then a previous one -
        // because we go up from the ocean, so we need to go higher and higher, so water can come down
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
                visit[row][col] ||
                heights[row][col] < prevValue) {
            return;
        }

        // water can reach ocean from here, set true. also it will serve as a visited marker
        visit[row][col] = true;

        // reach out to adjusted squares
        dfs(heights, visit, row + 1, col, heights[row][col]);
        dfs(heights, visit, row - 1, col, heights[row][col]);
        dfs(heights, visit, row, col + 1, heights[row][col]);
        dfs(heights, visit, row, col - 1, heights[row][col]);
    }
}
