package com.leetcode.medium.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * #253. Meeting Rooms II
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * (0,8),(8,10) is not conflict at 8
 *
 * Example1
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 *
 * Example2
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(
                new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))))); // 2

        System.out.println(minMeetingRooms(
                new ArrayList<>(List.of(new Interval(2, 7))))); // 1
    }

    // the idea - use min heap
    // 1. Sort the Meetings by Start Time: First, sort the meetings based on their start times.
    // 2. Use a Min-Heap to Track the End Times: Maintain a min-heap that stores the end times of meetings. This will help you keep track of the earliest available room.
    // 3. Allocate or Release Rooms: For each meeting:
    //  - If the earliest ending meeting is before or when the current meeting starts, free up that room by removing it from the heap.
    //  - Add the current meeting's end time to the heap to signify that a room is occupied.
    // 4. The size of the heap at the end will give you the minimum number of rooms required.

    // The min-heap will contain only active meetings (with their end times).
    // Every time we check the heap, we ensure that the earliest meeting has ended if the current meeting can start.
    // The final size of the heap will indicate the number of rooms needed because the heap keeps track of only those meetings that are currently in progress.
    public static int minMeetingRooms(List<Interval> intervals) {
        // essential to sort by start time
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        // to keep track of the earliest ending meeting
        PriorityQueue<Integer> meetingEnds = new PriorityQueue<>();
        meetingEnds.offer(intervals.get(0).end); // for now it's the first one's end

        for (int i = 1; i < intervals.size(); i++) {
            int curStart = intervals.get(i).start;
            int curEnd = intervals.get(i).end;

            // if previous meeting ends before or when current one starts
            if (meetingEnds.peek() <= curStart) {
                meetingEnds.poll(); // empty the room for a new meeting
            }

            meetingEnds.offer(curEnd); // add current meeting to the room
            // it might be the room which just emptied, if "if" above was true, or a new one
        }

        return meetingEnds.size(); // result is the current size of pq
    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
