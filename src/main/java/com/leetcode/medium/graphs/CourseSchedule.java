package com.leetcode.medium.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * #207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 */
public class CourseSchedule {

    public static void main(String[] args) {
//        boolean canFinish1 = canFinish(5, new int[][]{{1, 0}});
//        System.out.println(canFinish1); // true
//
//        boolean canFinish2 = canFinish(5, new int[][]{{1, 0}, {0, 1}});
//        System.out.println(canFinish2); // false
//
        boolean canFinish3 = canFinish(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {3, 4}});
        System.out.println(canFinish3); // true

//        boolean canFinish4 = canFinish(5, new int[][]{{0, 1}, {1, 2}, {2, 0}});
//        System.out.println(canFinish4); // false
    }

    // the idea - topological sorting with dfs.
    // first build the graph
    // (for input {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {3, 4}})
    // (array index = course: (prerequisite courses(also array indexes):
    // 0: (1, 2), 1: (3, 4), 2: (), 3: (4), 4: ()
    // then check with dfs search, if graph has a cycle
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // build graph
        // // this structure is a representation of a graph in code, also called an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // build an adjacency list, which represents direct graph
        // 0(1, 2), 1(3, 4), 2 (), 3(4), 4 ()
        // 0(1, 2) means that 0 is the prereq for 1 and 2
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }

        // check if graph has cycle
        int[] visited = new int[numCourses];
        for (int i = 0; i < graph.size(); i++) {
            if (visited[i] == 0 && hasCycle(graph, visited, i)) {
                return false;
            }
        }

        return true;
    }

    // dfs (check pics)
    private static boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true; // visiting, not ok - cycle
        }
        if (visited[course] == 2) {
            return false; // visited, ok
        }

        visited[course] = 1; // currently visiting

        for (int prereq: graph.get(course)) {
            if (hasCycle(graph, visited, prereq)) {
                return true;
            }
        }

        visited[course] = 2; // already visited in this DFS, no need to check anymore
        return false;
    }


    // Kahn's algorithm for topological sorting
    // see #210' solution, it's the same apart from the return
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        // this structure is a representation of a graph in code, also called an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegrees = new int[numCourses];
        for (int[] pair: prerequisites) {
            int course = pair[0];
            int preReq = pair[1];

            graph.get(preReq).add(course);
            inDegrees[course]++;
        }

        Queue<Integer> zeroInDegreeQ = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                zeroInDegreeQ.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!zeroInDegreeQ.isEmpty()) {
            int course = zeroInDegreeQ.poll();
            result.add(course);
            for (int dependent: graph.get(course)) {
                inDegrees[dependent]--;
                if (inDegrees[dependent] == 0) {
                    zeroInDegreeQ.offer(dependent);
                }
            }
        }

        return result.size() == numCourses;
    }
}
