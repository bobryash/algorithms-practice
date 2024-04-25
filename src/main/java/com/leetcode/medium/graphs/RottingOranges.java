package com.leetcode.medium.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * #994. Rotting Oranges
 *
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {

    private int freshCount = 0;

    public static void main(String[] args) {
        // Example 1
        System.out.println(new RottingOranges().orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        })); // 4


        // Example 2
        System.out.println(new RottingOranges().orangesRotting(new int[][]{
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        })); // -1


        // Example 3
        System.out.println(new RottingOranges().orangesRotting(new int[][]{
                {0, 2}
        })); // 0

        // Example 4
        System.out.println(new RottingOranges().orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        })); // -1
    }

    // the idea is the same as in #286 - use bfs, starting with rotten oranges.
    // keep track of fresh oranges (there might be oranges, not adjusted to any rotten one,
    // hence return -1)
    // add all rotten ones in a queue, then while queue is not empty and there are fresh oranges,
    // pop all oranges from the queue, do it in a for-loop to keep track of time,
    // in a for loop add all theirs adjusted oranges in the queue and mark them rotten
    // repeat
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    this.freshCount++;
                }
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        int minutes = 0;
        while (!q.isEmpty() && freshCount != 0) {
            // it's a for loop here, in comparison with walls and gates problem #286,
            // because here we need to keep track of time.
            // each layer will be equal to 1 time unit.
            // we keep track of it by processing current queue's size per for-loop:
            // enter while loop -> we have some oranges (initially they all are rotten) ->
            // -> we start for-each loop for each orange in the queue -> we pop all of them ->
            // -> we add all their neighbors to the queue (and mark them rotten) ->
            // -> increase time -> enter while loop ->...
            int qSize = q.size();
            for (int k = 0; k < qSize; k++) {
                int[] orange = q.poll();
                int i = orange[0];
                int j = orange[1];

                addOrange(grid, q, freshCount, i + 1, j);
                addOrange(grid, q, freshCount, i - 1, j);
                addOrange(grid, q, freshCount, i, j + 1);
                addOrange(grid, q, freshCount, i, j - 1);
            }

            // all oranges, which were adjusted to rotten ones, are marked as rotten
            // by this point, meanwhile 1 minute passed
            minutes++;
        }

        return this.freshCount == 0 ? minutes : -1;
    }

    private void addOrange(int[][] grid, Queue<int[]> q, int freshCount, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != 1) {
            return;
        }

        this.freshCount--;
        grid[i][j] = 2;
        q.offer(new int[]{i, j});
    }
}
