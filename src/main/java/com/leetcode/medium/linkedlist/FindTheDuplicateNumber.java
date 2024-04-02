package com.leetcode.medium.linkedlist;

import java.util.Arrays;

/**
 * #287. Find the Duplicate Number
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2})); // 2
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2})); // 3
        System.out.println(findDuplicate(new int[]{3 ,3 ,3 ,3 ,3})); // 3
    }

    // the idea - think of it as a linked list problem,
    // then apply Floyd's Tortoise and Hair algorithm with a trick (2 intersections).
    // think of array's values as pointers:
    // index(value): 0(1) -> 1(3) -> 3(2) -> 2(4) -> [4(2) -> 2(4) -> 4(2) ->...] <- cycle in linked list!
    public static int findDuplicate(int[] nums) {
        // always start with 0, because 0 never is a part of a cycle
        int slow = nums[0], fast = nums[0];

        // 1st intersection: fast meets slow.
        // why do-while:
        // by problem description - array contains values (1..n),
        // hence 0 will never be a part of a cycle,
        // and hence we need to move pointers at least once
        // to try to detect cycle.
        // (nums[0] will never be == 0, same as other indexes)
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 2nd intersection: new slow(start from 0) meets old slow(start from 1st intersection).
        // just a thing you need to remember:
        // distance from 1st intersection(slow meets fast)
        // and distance from the start of list (slow2)
        // are equal, and this 2nd point of intersection is always start of a cycle
        int slow2 = nums[0];
        while (slow2 != slow) {
            slow2 = nums[slow2];
            slow = nums[slow];
        }

        return slow;
    }
}
