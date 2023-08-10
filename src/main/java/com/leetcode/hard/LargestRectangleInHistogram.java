package com.leetcode.hard;

import java.util.Stack;

/**
 * #84. Largest Rectangle in Histogram
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // 10
        System.out.println(largestRectangleArea(new int[]{2, 4})); // 4
        System.out.println(largestRectangleArea(new int[]{2, 1, 2})); // 3
    }

    // main idea - keep adding heights to the stack. if previous height is bigger than current,
    // then pop it (because you can't extend the higher to the right no more - think of heights 4 2 with indexes 0 1)
    // and after you popped it, update the index of current,
    // because it CAN be extended in the direction of the popped one: (i:0,h:4),(i:1,h:2) -> (i:0,h:2)
    // try to draw - it's better to visualize
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            Pair curr = new Pair(i, heights[i]);
            while (!stack.isEmpty() && stack.peek().height > curr.height) {
                Pair popped = stack.pop();
                maxArea = Math.max((i - popped.index) * popped.height, maxArea);
                // imagine heights 4 and 2 with indexes 0 and 1
                // we popped 4, because it can't be extended to the right no more,
                // but we update index of height 2 (0 <- 1), because it can be extended to the left - in a direction of popped height 4
                curr.index = popped.index;
            }
            stack.push(curr);
        }

        int len = heights.length;
        while (!stack.isEmpty()) {
            Pair curr = stack.pop();
            maxArea = Math.max(maxArea, (len - curr.index) * curr.height); // since height wasn't popped, it means that it can be extended till the end
        }

        return maxArea;
    }

    static class Pair {
        int index;
        int height;

        Pair(int i, int h) {
            this.index = i;
            this.height = h;
        }

        @Override
        public String toString() {
            return "(i:" + index + ", h:" + height + ")";
        }
    }
}
