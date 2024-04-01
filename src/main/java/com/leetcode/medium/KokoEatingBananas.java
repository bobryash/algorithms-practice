package com.leetcode.medium;

/**
 * #875. Koko Eating Bananas
 * <p>
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k. Each hour,
 * she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * <p>
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * <p>
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * <p>
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8)); // 4
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5)); // 30
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6)); // 23
    }

    // the idea - maximum required speed (banana/hour) will be equal to maximum value among piles
    // e.g. [3, 6, 7, 11] - with 11 banana/hour Koko will eat the biggest pile and others with no problem
    // and we can take 1 as a minimum speed.
    // so basically we have a 1...maxPile numbers, and we can apply binary search,
    // so we can find minimum required speed, which will fit the x < h condition
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1;
        // find the maximum pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left <= right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middleSpeed = left + (right - left) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle),
            // e.g. 1 (banana)/2 (banana/hour) = 1 hour (remember, Koko do nothing after it finish with a pile)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middleSpeed);
            }
            // System.out.println(hourSpent);

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middleSpeed - 1; // speed is enough, but maybe she could be faster
            } else {
                left = middleSpeed + 1; // time is not enough to eat all, increase speed
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return left;
    }
}
