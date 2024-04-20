package com.leetcode.medium.priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * #973. K Closest Points to Origin
 *
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)^2 + (y1 - y2)^2).
 *
 * You may return the answer in any order.
 * The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */
public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{1, 3}, {-2, 2}}, 1))); // [[-2,2]]
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2))); // [[3,3],[-2,4]]
    }

    public static int[][] kClosest(int[][] points, int k) {
        // comparator here is same as for natural integer order, but for coordinates squares.
        // from lesser to bigger:
        // og input: {1, 3}, {-2, 2}, k = 1; in pq: {-2, 2}, {1, 3}; because squares: (8, 9)
        // 8 is lesser, hence it closer to the center (0, 0), hence it's the answer.
        // distance between points formula in the description: √(x1 - x2)^2 + (y1 - y2)^2),
        // where x2 and y2 is center coordinates (0, 0)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));

        for (int[] point: points) {
            pq.offer(point);
        }

        int[][] answer = new int[k][2];
        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll();
        }

        return answer;
    }
}
