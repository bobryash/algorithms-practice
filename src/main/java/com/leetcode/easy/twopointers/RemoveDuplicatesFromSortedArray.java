package com.leetcode.easy.twopointers;

import java.util.Arrays;

/**
 * #26. Remove Duplicates from Sorted Array
 *
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once.
 * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they
 * were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 *
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 2, 3, 4, 4, 5};
        int res = removeDuplicates(arr);

        System.out.println(res + " " + Arrays.toString(arr));
    }

    /**
     * j - fast pointer, traverses array
     * i - holds pointer to the last unique number
     *
     * While j goes through duplicates (e.g. 1ij, 1j, 1j), i stays still (1i, ),
     * but when j reach smth different (e.g. , 2j) then i goes i + 1 (in place of next duplicate, e.g 1, 1i, 1)
     * and then we put in i value to which j is pointing right now (1i, 1, 1, 2j -> 1, 1i, 1, 2j -> 1, 2i, 1, 2j)
     */
    // 1, 2, 2, 2, 3, 4, 4, 5
    //                      j
    // 1, 2, 3, 4, 5, 4, 4, 5
    //             i

    // 1, 1, 1, 2
    //          j
    // 1, 2, 1, 2
    //    i
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
