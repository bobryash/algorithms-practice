package com.leetcode.hard;

/**
 * #42. Trapping Rain Water
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5})); // 9
    }

    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftMax = height[left];
        int rightMax = height[right];

        int result = 0;

        while (left < right) {
            if (leftMax > rightMax) { // always move pointer which is smaller (we always need smaller, smaller holds the water!)
                right--; // move it first, because at the beginning you can't hold water - one boundary is missing (here - left one)
                // why we need only rightMax? - because we are looking at the right pointer value - closest to rightMax. and we know that rightMax is less than leftMax (row 34)
                // so it's our bottleneck, we should look at the minimum, because it will hold water. again, when calculating (max(left or right?) - height[right]) we always look for the MINIMUM value, so water doesn't spill!
                rightMax = Math.max(rightMax, height[right]); // no need to check for negative value below, because worst case would be zero - rightMax(=height[right]) - height[right] = 0
                result += rightMax - height[right];
            } else {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                result += leftMax - height[left];
            }
        }

        return result;
    }
}
