package com.leetcode.medium.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * #261. Graph Valid Tree
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input:
 * n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 *
 * Output: true
 *
 * Example 2:
 * Input:
 * n = 5
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 *
 * Output: false
 */
public class GraphValidTree {

    public static void main(String[] args) {
        System.out.println(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}})); // true
        System.out.println(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
    }

    // the idea - valid tree can't have loops, and have each node connected to others
    // check graph for these properties: first one with dfs, second with "visited" array.
    // important: graph is undirected!
    public static boolean validTree(int n, int[][] edges) {
        // adjacency list, which represents graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // since graph is undirected -
        // meaning its nodes doesn't have any particular direction,
        // so each node is connected to each neighbour
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // check: valid tree can't have loops.
        // by description - we have nodes 0...n-1, so start with node 0
        // -1 is for prev here - no prev for a start
        boolean[] visited = new boolean[n];
        if (hasCycle(graph, visited, 0, -1)) {
            return false;
        }

        // check: in valid tree each node should be connected.
        // there could be some nodes, which are not connected to others
        // meaning it's not a graph valid tree
        for (boolean v: visited) {
            if (!v) return false;
        }

        return true;
    }

    // super important to remember, that's while traversing a tree graph with dfs,
    // it's ok to visit previous nodes again, and it's not a cycle (would be a false positive): 0<->1<->2
    // we need to detect if there's a real cycle,like 0<->1<->2<->0,
    // and for this we have prevNode value which accompany each curNode.
    // steps to detect:
    // start with 0, it's unvisited - mark it as visited. it has neis 1 and 2, first go to left 1
    //      1 is unvisited - mark 1 as visited. 1 has neigs 0 and 2, first go to 0.
    //          0 is already visited, but it's prevNode, so it's ok.
    //          then go to 2
    //              2 is unvisited - mark 2 as visited. 2 has neis 1 and 0, first go to 1
    //                  1 is already visited, but it's prevNode, so it's ok
    //                  then go to 0
    //                      0 is ALSO already visited, BUT it's not the prevNode, meaning it's a cycle!
    private static boolean hasCycle(List<List<Integer>> graph, boolean[] visited, int curNode, int prevNode) {
        visited[curNode] = true;
        for (int nei: graph.get(curNode)) {
            if (!visited[nei]) { // we haven't visited this neighbour, so proceed with checks
                if (hasCycle(graph, visited, nei, curNode))
                    return true;
            } else if (nei != prevNode) { // node was already visited, but it's not a prevNode..
                return true; // ..meaning cycle
            }
        }

        return false;
    }
}
