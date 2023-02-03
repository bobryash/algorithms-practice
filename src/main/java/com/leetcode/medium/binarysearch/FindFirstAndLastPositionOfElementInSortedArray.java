package com.leetcode.medium.binarysearch;

import java.util.Arrays;

/**
 * #34
 * <p>
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] result = new FindFirstAndLastPositionOfElementInSortedArray()
                .searchRange(new int[]{5, 7, 8, 8, 8, 10}, 8);

        System.out.println(Arrays.toString(result)); // 2, 4
    }

    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;

    }

    public int findFirst(int[] nums, int target) {
        int result = -1;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else { // nums[mid] == target
                result = mid;

                // because nothing after mid
                // can be the first occurrence of target.
                // maybe mid is the first occurrence, maybe not
                // so let's narrow the target for [0...mid-1] and find out
                end = mid - 1;
            }
        }

        return result;
    }

    public int findLast(int[] nums, int target) {
        int result = -1;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else { // nums[mid] == target
                result = mid;

                // because nothing before mid
                // can be the last occurrence of target.
                // maybe mid is the last occurrence, maybe not
                // so let's narrow the target for [mid+1...high] and find out
                start = mid + 1;
            }
        }

        return result;
    }
}
