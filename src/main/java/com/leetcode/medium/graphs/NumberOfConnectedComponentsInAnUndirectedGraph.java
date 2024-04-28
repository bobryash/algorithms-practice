package com.leetcode.medium.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * #323. Number of Connected Components in an Undirected Graph
 *
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b]
 * means that there is an edge between node a and node b in the graph.
 *
 * Return the total number of connected components in that graph.
 *
 * Example 1:
 * Input:
 * n=3
 * edges=[[0,1], [0,2]]
 * Output: 1
 *
 * Example 2:
 * Input:
 * n=6
 * edges=[[0,1], [1,2], [2,3], [4,5]]
 * Output: 2
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public static void main(String[] args) {
        System.out.println(new NumberOfConnectedComponentsInAnUndirectedGraph()
                .countComponents2(3, new int[][]{{0, 1}, {0, 2}})); // 1
        System.out.println(new NumberOfConnectedComponentsInAnUndirectedGraph()
                .countComponents2(6, new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 5}})); // 2
    }

    // solution #1 - Union-Find algorithm
    // keep two arrays - parent and rank.
    // parent - who is the parent of this node. by default each node is its own parent.
    // rank - how many nodes connected to this one. by default value for each node is 1 (each node connected to itself).
    // then go through each edge, and do the union: find their parents, compare them,
    // if parents are equal - they are already connected, no need to union them,
    // if not - attach parent with lesser rank to a bigger one, and update parent rank with a value of lesser rank.
    // each union means a decrement of a total num of elements (n), return whatever is left of n.

    int[] rank;
    int[] parent;

    public int countComponents1(int n, int[][] edges) {
        rank = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int result = n;
        for (int[] edge: edges) {
            if (union(edge[0], edge[1]) == 1) {
                result--;
            }
        }

        return result;
    }

    private int union(int n1, int n2) {
        int p1 = findParent(n1);
        int p2 = findParent(n2);

        if (p1 == p2) {
            return 0;
        }

        if (p1 > p2) {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        } else {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        }

        return 1;
    }

    private int findParent(int node) {
        int result = node;
        while (parent[result] != result) {
            result = parent[result];
        }

        return result;
    }

    // solution #2 - DFS
    public int countComponents2(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            graph.get(edge[1]).add(edge[0]);
            graph.get(edge[0]).add(edge[1]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i);
                count++; // each dfs finished - means one entire graph is visited
            }
        }

        return count;
    }

    private void dfs(boolean[] visited, List<List<Integer>> graph, int node) {
        visited[node] = true;
        for (int nei: graph.get(node)) {
            if (!visited[nei]) {
                dfs(visited, graph, nei);
            }
        }
    }
}
