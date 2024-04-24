package com.leetcode.medium.graphs;

import java.util.*;

/**
 * #286. Walls and Gates
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF
 * as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a Gate, that room should remain filled with INF
 *
 * Example1
 *
 * Input:
 * [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],
 * [2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output:
 * [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 *
 * Explanation:
 * the 2D grid is:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * the answer is:
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 * Example2
 *
 * Input:
 * [[0,-1],[2147483647,2147483647]]
 * Output:
 * [[0,-1],[1,2]]
 */
public class WallsAndGates {

    private static Set<int[]> visited = new HashSet<>();

    public static void main(String[] args) {
        int[][] example1 = new int[][]{
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        };
        wallsAndGates(example1);
        // [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
        System.out.println(Arrays.deepToString(example1));

        int[][] example2 = new int[][]{
                {0, -1},
                {2147483647, 2147483647}
        };
        wallsAndGates(example2);
        // [[0,-1],[1,2]]
        System.out.println(Arrays.deepToString(example2));
    }

    // the idea - start with gates, and use bfs starting from them.
    // first add all gates to the queue, then go through it and set first layer:
    // first layer sets distance 1 to all rooms which are connected to that gate.
    // pop each gate and add all rooms that they updated to the queue.
    // do the second layer with them - set 2 for all their adjusted rooms, etc..
    // you mark room as visited by setting distance value to them:
    // now they are not INF and not -1 (check addRoom method condition)
    public static void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        // initially add all gates to the queue.
        // then all gates will be polled and all adjusted rooms (1st layer) will be added.
        // and so on and so forth, layer by layer
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) { // gate == 0
                    q.offer(new int[]{i, j});
                }
            }
        }

        // it's ok to set 0 first to gate (gate by description is 0)
        int distance = 0;
        while (!q.isEmpty()) {
            int[] room = q.poll();
            int i = room[0];
            int j = room[1];
            // set current distance to the room
            rooms[i][j] = distance;

            // add all adjusted rooms to the next layer
            addRoom(rooms, q, i + 1, j);
            addRoom(rooms, q, i - 1, j);
            addRoom(rooms, q, i, j + 1);
            addRoom(rooms, q, i, j - 1);

            // next layer will have this distance set
            distance++;
        }
    }

    private static void addRoom(int[][] rooms, Queue<int[]> queue, int i, int j) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        // if out of bounds or if wall(-1) or if visited(value is not INF)
        if (i < 0 || i >= rows ||
                j < 0 || j >= cols ||
                rooms[i][j] == -1 || rooms[i][j] != Integer.MAX_VALUE) {
            return; // skip
        }

        // add to next layer
        queue.offer(new int[]{i, j});
    }
}
