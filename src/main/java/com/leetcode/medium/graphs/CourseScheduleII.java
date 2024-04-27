package com.leetcode.medium.graphs;

import java.util.*;

/**
 * #210. Course Schedule II
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseScheduleII {

    public static void main(String[] args) {
        int[] topOrder1 = findOrder(2, new int[][]{
                {1, 0}
        });
        System.out.println(Arrays.toString(topOrder1)); // [0,1]

        int[] topOrder2 = findOrder(4, new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
       });
        System.out.println(Arrays.toString(topOrder2)); // [0, 1, 2, 3]

        int[] topOrder3 = findOrder(1, new int[][]{});
        System.out.println(Arrays.toString(topOrder3)); // [0]

        int[] topOrder4 = findOrder(5, new int[][]{
                {0, 1},
                {0, 2},
                {1, 3},
                {1, 4},
                {3, 4}
        });
        System.out.println(Arrays.toString(topOrder4)); // [2, 4, 3, 1, 0]
    }

    // the goal - to return the ordering of courses you should take to finish all courses.
    // the idea - to use Kahn's topology sorting algorithm:
    // add to the queue all the nodes, which have zero in-degrees (edges which come into a node)
    // then pop them from the queue, add to result, then go through their neighbors
    // (dependent nodes) and decrease theirs in-degrees (since we popped their prerequisite).
    // check the updated in-degree, if it's 0, then add node to the queue as well, repeat while !queue.isEmpty.
    // if result != number of nodes, then there's a cycle and topological sort can't be performed
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // build a graph, where edges are directed from prerequisites to dependent nodes (check pics)
        // this structure is a representation of a graph in code, also called an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegrees = new int[numCourses];
        for (int[] pair: prerequisites) {
            int course = pair[0];
            int preReq = pair[1];

            // prerequisite is connected to a dependent node (e.g. 4 -> 3, 4 -> 1)
            graph.get(preReq).add(course);
            // dependent node got a new edge come in from prerequisite, so increment its in-degree
            inDegrees[course]++;
        }

        // find nodes with 0 in-degree
        Queue<Integer> zeroInDegreesQ = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                zeroInDegreesQ.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!zeroInDegreesQ.isEmpty()) {
            int course = zeroInDegreesQ.poll();
            // node with 0 in-degree goes straight to the result
            result.add(course);
            for (int dependent: graph.get(course)) {
                inDegrees[dependent]--; // popped prerequisite, so decrease node's in-degree
                if (inDegrees[dependent] == 0) {
                    zeroInDegreesQ.offer(dependent); // now node is eligible for the result
                }
            }
        }

        if (result.size() != numCourses) { // all nodes should be processed, if not - there's a cycle
            return new int[0];
        }

        // convert list to array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
