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
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * Example 3:
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
        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // - 1
        System.out.println(search2(new int[]{1}, 0)); // -1
        System.out.println(search2(new int[]{3, 1}, 1)); // 1
    }

    // rotated array is an array, which parts were swapped:
    // x|     /     | /
    //  |    /      |/
    //  |   /       |     /
    //  |  /        |    /
    //  | /         |   /
    //  _________   ___________y
    //   1 2 3 4 5  4 5 1 2 3
    // we can find out in which half our mid is.
    //
    // the idea - find out in which half your mid is, then decide if you should move to other half or not.
    // decision is based on comparison to mid-value and closest far extreme (left or right)
    //
    static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[mid] >= nums[left]) { // we in the left part (check graph 2 above)
                // when do we move from left part to right?
                // 1. when target is greater than mid: [2, 4, 5, 6, 7, 0, 1] target 7 > mid 6
                // OR
                // 2. when target is less than far left value: [2, 4, 5, 6, 7, 0, 1] target 1 < left 2
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // we in the right part (check graph 2 above)
                // when do we move form right part to left?
                // 1. when target is less than mid: [6, 7, 0, 1, 2, 4, 5] target 5 > mid 7
                // OR
                // 2. when target is greater than far right value: [6, 7, 0, 1, 2, 4, 5] target 6 > right 5
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    // little recap.
    // first need to determine which side is sorted, relative to pivot.
    // then try to check if target is in sorted, then decide to which part go next, based on that
    static int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) { // left part is sorted (it's not a guarantee that target is there though)
                if (nums[left] <= target && target < nums[mid]) { // target on a left part
                    right = mid - 1; // go left
                } else { // target on a right side
                    left = mid + 1; // go right
                }
            } else { // right part is sorted
                if (nums[mid] < target && target <= nums[right]) { // target on a right side
                    left = mid + 1; // go right
                } else { // target on a left side
                    right = mid - 1; // go left
                }
            }
        }

        return -1;
    }
}
