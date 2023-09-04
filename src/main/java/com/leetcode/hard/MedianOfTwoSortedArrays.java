package com.leetcode.hard;

/**
 * #4. Median of Two Sorted Arrays
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * <p>
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // 2.5
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1, 2, 3, 4})); // 3.5
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 2, 3, 4})); // 3.0
    }

    /*
      the main idea is to correctly divide both arrays (find right partitions).

      by "right" I mean when all the elements on left side of both partitions of both arrays are less than
      elements on right side of both partitions of both arrays:
      x: [ 1 2 3=xLeft|xRight=4 ]
      y: [ 1 2 3=yLeft|yRight=4 5 6 7 8 ]
      3(xLeft) is less than 4(xRight) in x, we know it because array is ordered, no need to check it.
      but we need to check if 3(xLeft) is less than elements on a right side in other array (yRight), and same for yLeft-xRight pair.
      [ 1 1 2 2 3 3 | 4 4 5 6 7 8] - median is (3 + 4) / 2 = 3.5 (the goal basically is to find that middle of merged array)

      how do we partition arrays, where do we start? - we use binary search approach for the smaller array - [1 2 3 4].
      when we draw a "division line" (partitionX) in a middle of that array, we say that to left is part of the elements
      of left partition (e.g. [1 2 3] of  [1 1 2 2 3 3 |..]).
      and to the right is part of the elements of big right partition (e.g. [4] of ..| 4 4 5 6 7 8).

      how to find rest of the elements in bigger array? [ 1 2 3 4 5 6 7 8].
      easy - we know that partitions should be equal, thus it's equal to half = (num1.length + num2.length + 1) / 2.
      we already choose the start number for part of the elements in lesser array, then for bigger array it would be
      partitionY = half - partitionX

      if partitions are wrong (left elements bigger than right ones):
      x: [ 1 2=xLeft|xRight=3 4 ]
      y: [ 1 2 3 4=yLeft|yRight=5 6 7 8 ]
      (4=yLeft > 3=xRight)
      then we apply binary search to lesser array (because it's going to be faster)
      if xLeft > yRight -> move to lesser -> right = partitionX - 1, and etc.
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int half = (nums1.length + nums2.length + 1) / 2; // +1 is to deal with odd-length arrays ([1,3] [2]). no effect on even arrays ([1,2] [3,4])
        int left = 0, right = nums1.length;

        while (left <= right) {
            int partitionX = left + (right - left) / 2;
            int partitionY = half - partitionX;

            int xLeft = getLeft(nums1, partitionX);
            int xRight = getRight(nums1, partitionX);
            int yLeft = getLeft(nums2, partitionY);
            int yRight = getRight(nums2, partitionY);

            if (xLeft <= yRight && yLeft <= xRight) { // partitions are correct
                if ((nums1.length + nums2.length) % 2 == 0) { // total array is even
                    return (Math.max(xLeft, yLeft) + Math.min(xRight, yRight)) / 2.0;
                } else { // total array is odd
                    return Math.max(xLeft, yLeft);
                }
            } else { // partitions are incorrect
                if (xLeft > yRight) {
                    right = partitionX - 1;
                } else {
                    left = partitionX + 1;
                }
            }
        }

        return -1;
    }

    private static int getLeft(int[] nums, int partition) {
        if (partition == 0)
            return Integer.MIN_VALUE; // to deal with edge cases when array is empty or is of single-value
        return nums[partition - 1];
    }

    private static int getRight(int[] nums, int partition) {
        if (partition == nums.length) return Integer.MAX_VALUE; // same
        return nums[partition];
    }
}
