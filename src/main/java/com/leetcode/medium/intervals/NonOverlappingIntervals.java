package com.leetcode.medium.intervals;

import java.util.Arrays;

/**
 * #435. Non-overlapping Intervals
 *
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}})); // 1
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}})); // 2
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}})); // 0
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        // essential to order intervals first
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        int intervalsToRemove = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            if (lastEnd <= curStart) { // no overlap, just update lastEnd to curEnd
                lastEnd = curEnd;
            } else { // overlap, choose minimum end to minimize the risk of the next overlap
                lastEnd = Math.min(lastEnd, curEnd);
                intervalsToRemove++; // no need to actually remove an interval, just update counter
            }
        }

        return intervalsToRemove;
    }
}
