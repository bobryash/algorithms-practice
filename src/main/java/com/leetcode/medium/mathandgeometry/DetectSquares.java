package com.leetcode.medium.mathandgeometry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * #2013. Detect Squares
 *
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure.
 * Duplicate points are allowed and should be treated as different points.
 *
 * Given a query point, counts the number of ways to choose three points from the data structure
 * such that the three points and the query point form an axis-aligned square with positive area.
 *
 * An axis-aligned square is a square whose edges are all the same length and are either parallel
 * or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 * Example 1:
 *
 *
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 */
public class DetectSquares {

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        detectSquares.count(new int[]{11, 10}); // return 1. You can choose:
                                                //   - The first, second, and third points
        detectSquares.count(new int[]{14, 8});  // return 0. The query point cannot form a square with any points in the data structure.
        detectSquares.add(new int[]{11, 2});    // Adding duplicate points is allowed.
        detectSquares.count(new int[]{11, 10}); // return 2. You can choose:
                                     //   - The first, second, and third points
                                     //   - The first, third, and fourth points
    }

    // stores occurrences of points (stream can contain duplicates!)
    // each point occurrence counts as separate, builds a new square
    private Map<Point, Integer> pointCounts;

    public DetectSquares() {
        pointCounts = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        Point pointObj = new Point(x, y);
        pointCounts.put(pointObj, pointCounts.getOrDefault(pointObj, 0) + 1);
    }

    public int count(int[] point) {
        // query point's x and y
        int x = point[0];
        int y = point[1];

        int squareCount = 0;

        // we search ONLY for diagonal points (in position to query point)
        // because it simplifies from O(n^3) to O(n) - find adjusted to diagonals with hashmap
        for (Point p2: pointCounts.keySet()) {
            // can't be diagonal, if aligned on absciss or ordinate
            if (p2.x == x || p2.y == y) continue;

            // verify that they are diagonal (check pic)
            if (Math.abs(x - p2.x) == Math.abs(y - p2.y)) {
                Point p3 = new Point(x, p2.y);
                Point p4 = new Point(p2.x, y);
                // verify that adjusted points exist
                if (pointCounts.containsKey(p3) && pointCounts.containsKey(p4)) {
                    // multiply considering occurrences of points, which gives total num of squares
                    squareCount += pointCounts.get(p2) * pointCounts.get(p3) * pointCounts.get(p4);
                }
            }
        }

        return squareCount;
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (getClass() != o.getClass()) return false;
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
