package com.leetcode.medium.binarysearch;

/**
 * #33
 * <p>
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(new SearchInRotatedSortedArray()
                .search(new int[]{3, 1}, 1));
    }

    // We keep dividing the array into two parts by checking the middle element and moving the low and high
    // pointers accordingly, until we find the target or reach the end of the array.
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) { // [4, 5, 6, 7, 0, 1, 2] 4 < 7 // we're in a left sorted part
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1; // [4, 5, 6] - 5
                } else {
                    start = mid + 1; // [0, 1, 2] - 1
                }
            } else { // [7, 0, 1, 2, 4, 5, 6] 7 > 2 // we're in a right sorted part
                if (target > nums[mid] && target <= nums[end]) { // if target in a right part, then forget left part
                    start = mid + 1; // [4, 5, 6] - 5
                } else { // go to the left, if not
                    end = mid - 1; // [7, 0, 1] - 0
                }
            }
        }

        return -1;
    }
}
