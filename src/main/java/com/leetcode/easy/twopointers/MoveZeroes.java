package com.leetcode.easy.twopointers;

import java.util.Arrays;

/**
 * #283
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12}; // 1 0 0 0 1 0 2 0 3
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums)); // 1, 3, 12, 0, 0
    }

    public static void moveZeroes(int[] nums) {
        int indexReservedForNonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[indexReservedForNonZero];
                nums[indexReservedForNonZero] = nums[i];
                nums[i] = temp;
                indexReservedForNonZero++;
            }
        }
    }
}
