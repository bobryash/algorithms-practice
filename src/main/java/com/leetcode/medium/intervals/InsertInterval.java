package com.leetcode.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #57. Insert Interval
 *
 * You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [starti, endi] represent the start and the end of the ith interval and
 * intervals is sorted in ascending order by starti. You are also given an interval
 * newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order
 * by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public static void main(String[] args) {
        // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));

        // [[1,2],[3,10],[12,16]]
        System.out.println(Arrays.deepToString(insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));

        // [[0,1],[2,5],[6,7],[8,9]]
        System.out.println(Arrays.deepToString(insert(new int[][]{{2, 5}, {6, 7}, {8, 9}}, new int[]{0, 1})));

    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // can put newInterval in result, and also all intervals starting from current..
            if (newInterval[1] < intervals[i][0]) { // [1, 2](newInterval) < [3, 4](interval)
                result.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]); //.. and return result
                }
                return result.toArray(new int[result.size()][2]);
                // newInterval doesn't overlap with current one, so interval can be put in result
                // but newInterval still can overlap next ones, so keep cycle going
            } else if (intervals[i][1] < newInterval[0]) { // [3, 4](interval) [5,10](newInterval)
                result.add(intervals[i]);
                // newInterval overlaps with current one
                // so create new newInterval with min starts and max ends from these two
            } else { // [2, 4](interval) [1, 5](newInterval)
                newInterval = new int[]{Math.min(newInterval[0], intervals[i][0]),
                        Math.max(newInterval[1], intervals[i][1])};
            }
        }

        // if we got to this poing, newInterval is not added to result yet
        result.add(newInterval);
        return result.toArray(new int[result.size()][2]);
    }
}
