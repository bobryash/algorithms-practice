package com.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * #739. Daily Temperatures
 * <p>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * <p>
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * <p>
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30, 40, 50, 60}))); // [1,1,1,0]
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30, 60, 90}))); // [1,1,0]
    }

    // the idea - add temps in a stack, until we find temp which is bigger than previous one (on top of stack)
    // this big temp might be bigger than several in a row, so we go in while loop, find difference (indexes == day's numbers) and populate the result with diffs
    // and then push current temp in stack
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int currDay = 0; currDay < temperatures.length; currDay++) {
            while (!stack.isEmpty() && temperatures[currDay] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return result;
    }
}
