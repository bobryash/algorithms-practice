package com.leetcode.easy.twopointers;

import java.util.Arrays;

/**
 * #88. Merge Sorted Array
 * <p>
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * <p>
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be
 * merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        // 1
        int[] nums11 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums21 = new int[]{2, 5, 6};

        merge(nums11, 3, nums21, 3);
        System.out.println(Arrays.toString(nums11));

        // 2
        int[] nums12 = new int[]{1};
        int[] nums22 = new int[0];

        merge(nums12, 1, nums22, 0);
        System.out.println(Arrays.toString(nums12));

        // 3
        int[] nums13 = new int[]{0};
        int[] nums23 = new int[]{1};

        merge(nums13, 0, nums23, 1);
        System.out.println(Arrays.toString(nums13));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int last1 = m - 1; // Index of last element in nums1
        int last2 = n - 1; // Index of last element in nums2
        int lastMerged = m + n - 1; // Index of last element in the merged array

        // Merge elements from the end of the arrays
        while (last1 >= 0 && last2 >= 0) {
            if (nums1[last1] > nums2[last2]) {
                nums1[lastMerged] = nums1[last1];
                last1--;
            } else {
                nums1[lastMerged] = nums2[last2];
                last2--;
            }
            lastMerged--;
        }

        // If there are remaining elements in nums2, copy them to nums1
        while (last2 >= 0) {
            nums1[lastMerged] = nums2[last2];
            last2--;
            lastMerged--;
        }
    }
}
