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
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * <p>
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2})); // 1
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // 0
        System.out.println(findMin(new int[]{11, 13, 15, 17})); // 11

        System.out.println(findMin(new int[]{3, 1, 2})); // mid < right -> right = mid
        System.out.println(findMin(new int[]{2, 3, 1})); // mid > right -> left = mid + 1
    }

    // rotated array is an array, which parts were swapped:
    // x|     /     | /
    //  |    /      |/
    //  |   /       |     /
    //  |  /        |    /
    //  | /         |   /
    //  _________   ___________y
    //   1 2 3 4 5  4 5 1 2 3

    // the idea - shift l and r until we either land in the sorted range and then return l : [5,6,1,2,3] -> [1,2,3] -> 1
    // or until our range is narrowed to one value: [5,6,1,2,3] -> [1] -> 1.

    // how do we shift pointers? - first we determine in which half of rotated array current mid is [5,6] or [1,2,3]
    // if mid is in left(with bigger nums), then move left to mid + 1; [5,6,1,2,3] mid6 -> [1,2,3]
    // if mid in right half(with less nums), then move to left, but keep mid (cause it was on a left initially and might be the minimum): [5,6,1,2,3] -> [5,6,1]
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            // you CAN'T land into sorted range, which doesn't have a minimum
            if (nums[left] <= nums[right]) { // we are in a sorted portion [3, 4, 5, 1, 2] -> [1, 2]
                return nums[left];
            }

            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) { // means that mid is in the left part with bigger numbers: [3, 4, 5, 1, 2] mid 5 -> [3, 4, 5]
                left = mid + 1; // need to go to the right to [1, 2]
            } else { // mid already on a right side with lower nums: [4, 5, 1, 2, 3] 1 !>= 4
                right = mid; // going left, but we don't want to exclude mid from the range in this case (because it's on a right side, which has lower numbers)
            }
        }

        return 0;
    }
}
