package com.leetcode.easy.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * #252. Meeting Rooms
 *
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * (0,8),(8,10) is not conflict at 8
 *
 * Example1
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: false
 * Explanation:
 * (0,30), (5,10) and (0,30),(15,20) will conflict
 *
 * Example2
 * Input: intervals = [(5,8),(9,15)]
 * Output: true
 * Explanation:
 * Two times will not conflict
 */
public class MeetingRooms {

    public static void main(String[] args) {
        System.out.println(canAttendMeetings(
                new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))))); // false
        System.out.println(canAttendMeetings(
                new ArrayList<>(List.of(new Interval(5, 8), new Interval(9, 15))))); // true
    }

    public static boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        int lastEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            int curStart = intervals.get(i).start;
            int curEnd = intervals.get(i).end;
            if (lastEnd > curStart) {
                return false;
            } else {
                lastEnd = curEnd;
            }
        }

        return true;
    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
