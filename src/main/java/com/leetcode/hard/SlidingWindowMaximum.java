package com.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * #239. Sliding Window Maximum
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7      3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        // System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1))); // [1]
    }

    // the idea - sliding window
    // 1. check if left value in queue is out of window
    // 2. check if value we're adding to the queue is less than ones that already there (decreasing order in queue!)
    // 3. if we formed a full window - save window's max value in result (just take first one from queue - guaranteed to be the biggest!)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;

        // Create a deque to efficiently maintain maximum values in the current window.
        // super important to remember, that it should store values in decreasing order, so we know that first value is the biggest one [4,3,1] - peekFirst - 4
        // we need to add checks to keep this guarantee
        Deque<Integer> queue = new ArrayDeque<>();

        // sliding window
        // indexes: i - right border, i - k + 1 - left border
        for (int i = 0; i < nums.length; i++) {
            // Remove elements from the front (left) of the queue if they are outside the current window.
            // (check if left value in queue is outside current window)
            if (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }

            // Remove smaller elements from the back (right) of the queue to maintain decreasing order.
            // (check if current value is bigger than values from right -
            // because we want queue to store values in decreasing order, so we can be sure that first one is the biggest)
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            // Add the current element's index to the queue.
            queue.offer(i);

            // When the window contains k elements, record the maximum value in the result array.
            // (we formed at least one full window - time to save this window's biggest value, and keep doing this for next windows)
            if (i >= k - 1) {
                result[resultIndex] = nums[queue.peekFirst()];
                resultIndex++;
            }
        }

        // Return an array containing maximum values for each window.
        return result;
    }
}
