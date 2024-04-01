package com.leetcode.medium.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.util.Printer.getFlatList;

/**
 * #15. 3Sum
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * <p>
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * <p>
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(getFlatList(threeSum(new int[]{-1, 0, 1, 2, -1, -4}))); // ([-1, -1, 2], [-1, 0, 1])
        System.out.println(getFlatList(threeSum(new int[]{0, 1, 1}))); // ()
        System.out.println(getFlatList(threeSum(new int[]{0, 0, 0}))); // ([0, 0, 0])
    }

    // (looks scary but not so scary)
    // the idea - sort array, then go one by one element and apply 2sum solution for the rest elements relative to element.
    // given [2, 3, 8, 9]: [2(i), 3(l), 8, 9(r)] -> [2, 3(i), 8(l), 9(r)] -> etc..
    // it's important to skip duplicates, due to task requirements (unique values)
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // allows to apply two-pointer approach - we can say if elements sum is > or < than target sum

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) { // -2 because we need to be sure that there's enough space for a triplet
            if (i == 0 || nums[i] != nums[i - 1]) { // to skip duplicates for i: [2(i),2,3,8,9] --> [2,2,3(i),8,9] (==0 is ok because it's a start)
                int left = i + 1; // [2(i), 3(left), 8, 9]
                int right = nums.length - 1; // [2(i), 3(left), 8, 9(right)]

                // it's basically a 2sum solution from here, check 167 for details
                while (left < right) {
                    int threeSum = nums[i] + nums[left] + nums[right];
                    if (threeSum == 0) { // our goal - triplet which gives 0 in sum
                        result.add(List.of(nums[i], nums[left], nums[right]));

                        // to skip duplicates inside left and right [2(i), 3(left), 5, 5, 6, 7(right)]
                        while (left < right && nums[left] == nums[left + 1])
                            left++; // e.g. [1,2,2,2,3,4] --> [1(i),2(l),2,2,3,4(r)] -> [1(i),2,2,2(l),3,4(r)]
                        while (left < right && nums[right] == nums[right - 1])
                            right--; // e.g. [1,2,3,4,4,4] --> [1(i),2(l),3,4,4,4(r)] -> [1(i),2(l),3,4(r),4,4]
                        left++; // since 2 was already used - move to next: [1(i),2,2,2,3(l),4(r)]
                        right--; // since 4 was already used - move to next: [1(i),2(l),3(r),4,4,4]
                    } else if (threeSum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

        return result;
    }
}
