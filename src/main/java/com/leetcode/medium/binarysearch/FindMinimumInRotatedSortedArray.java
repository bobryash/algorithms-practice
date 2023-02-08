package com.leetcode.medium.binarysearch;

/**
 * #153
 * <p>
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 * <p>
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        //System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{3, 4, 5, 1, 2})); // 1
        //System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // 0
        //System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{11, 13, 15, 17})); // 11
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{3, 1, 2})); // mid < right -> right = mid
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{2, 3, 1})); // mid > right -> left = mid + 1
    }

    // If nums[mid] < nums[right], it means that the minimum element is in the left half of the array (including mid).
    // So we update right to mid.
    //
    // Otherwise, if nums[mid] >= nums[right], it means that the minimum element is in the right half of the array
    // (excluding mid). So we update left to mid + 1.
    //
    // Finally, when left == right, we return nums[left] as the minimum element.
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
