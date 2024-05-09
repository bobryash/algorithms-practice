package com.leetcode.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #56. Merge Intervals
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */
public class MergeIntervals {

    public static void main(String[] args) {
        // [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));

        // [[1,5]]
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
    }

    // the idea - compare end value of previous interval with start value of a current one.
    // first of all sort intervals based on theirs start value.
    // then start comparing starts:
    // case 1 - overlap:
    // [1, 3], [2, 6] -> [1, 3(prevEnd)], [2(curStart), 6] -> 2 < 3
    // we don't add current interval to the result, but we update previous interval's end value
    // because current interval should have bigger end value, so in result we update it to [1, 6]
    // case 2 - no overlap:
    // [1, 6], [8, 10] - we just add current interval to the result
    // N.B.: in case if prevEnd and curStart are equal [1, 4], [4, 7] it's count as an overlap - [1, 7]
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 0; i < intervals.length; i++) {
            int lastIndex = result.size() - 1;
            int lastEnd = result.get(lastIndex)[1];
            int curStart = intervals[i][0];

            if (curStart <= lastEnd) {
                result.get(lastIndex)[1] = Math.max(result.get(lastIndex)[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}
