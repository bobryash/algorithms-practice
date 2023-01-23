package com.leetcode.easy.binarysearch;

/**
 * #35
 * <p>
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int position = searchInsert(new int[]{1, 2, 7}, 4);

        System.out.println(position);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (right - left)/2 + left;
            int x= nums[mid];

            if (x == target) {
                return mid;
            } else if (x < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left + " " + right + " " + mid);
        return nums[mid] > target ? mid : mid + 1;
    }
}
