package com.leetcode.easy;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1,2,3,6,9}; // len 5
        System.out.println(search(nums, 9)); // 4
        System.out.println(search(nums, -1)); // 0
        System.out.println(search(nums, 20)); // -1
        System.out.println(search(nums, -11)); // -1
    }

    public static int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int curr = 0;
        int pivot = 0;
        while (left <= right) {
            pivot = (right - left)/2;
            curr = nums[pivot];
            System.out.println("before "+left + " " + right + " /" + pivot);
            if (curr == target) {
                return pivot;
            }
            if (curr > target) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
            System.out.println("after  " + left + " " + right + "\n");
        }
        return - 1;
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int curr = 0;
        while (left <= right) {
            int pivot = left + (right - left)/2;
            curr = nums[pivot];
            if (curr == target) {
                return pivot;
            }
            if (curr > target) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return -1;
    }
}
