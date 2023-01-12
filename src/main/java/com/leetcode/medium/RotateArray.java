package com.leetcode.medium;

import java.util.Arrays;

/**
 * #189
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums)); // [5, 6, 7, 1, 2, 3, 4]
    }

    /**
     * nums = "----->-->"; k =3
     * result = "-->----->";
     *
     * reverse "----->-->" we can get "<--<-----"
     * reverse "<--" we can get "--><-----"
     * reverse "<-----" we can get "-->----->"
     */
    public static void rotate(int[] nums, int k) {
        k %= nums.length; // because if k > nums.length -> overflow. 3%7=3,4%7=4,15%7=1-(leftover 7+7+1)
        reverse(nums, 0, nums.length - 1); // 1234567-> 7654321
        reverse(nums, 0, k - 1); // 7654321 -> 3456721
        reverse(nums, k, nums.length - 1); // 3456721 -> 3456712
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

//    public static void whenItemsIsAddedToCircularQueue_thenNoArrayIndexOutOfBounds() {
//        int QUEUE_CAPACITY= 10;
//        int[] circularQueue = new int[QUEUE_CAPACITY];
//        int itemsInserted = 0;
//        for (int value = 0; value < 1000; value++) {
//            int writeIndex = ++itemsInserted % QUEUE_CAPACITY;
//            System.out.println(writeIndex);
//            circularQueue[writeIndex] = value;
//        }
//    }
}
