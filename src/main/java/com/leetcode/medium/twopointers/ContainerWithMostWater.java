package com.leetcode.medium.twopointers;

/**
 * #11. Container With Most Water
 * <p>
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * <p>
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{1, 1}));
    }

    // the idea - place two pointers in the beginning and in the end,
    // then calculate current area, updated the result if it's bigger than a previous one,
    // then move a pointer which is lesser than other one
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]); // check the picture for visual understanding
            maxArea = Math.max(area, maxArea);

            if (height[left] > height[right]) {
                right--;
            } else if (height[left] < height[right]) {
                left++;
            } else {
                // edge case when left and right value are equal.
                // doesn't really matter which pointer to shift in this case.
                // you can even combine else-if above and this one.
                left++;
            }
        }

        return maxArea;
    }
}
