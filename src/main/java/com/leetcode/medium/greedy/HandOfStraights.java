package com.leetcode.medium.greedy;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * #846. Hand of Straights
 *
 * Alice has some number of cards and she wants to rearrange the cards into groups so
 * that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
 * return true if she can rearrange the cards, or false otherwise.
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 */
public class HandOfStraights {

    public static void main(String[] args) {
        // sorted hand [1, 2, 2, 3, 3, 4, 6, 7, 8]
        System.out.println(isNStraightHand2(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3)); // true
        System.out.println(isNStraightHand2(new int[]{1, 2, 3, 4, 5}, 4)); // false
    }

    // the idea - use greedy approach - sort cards and count each of their frequencies.
    // then start with the smallest card and try to create a group,
    // decreasing count of each card in a process.
    // if there is a gap (3,..(no 4), 5), then group creation is impossible.
    // (don't forger to first check if division is actually possible)

    // What makes it greedy is the local optimization strategy it employs: at each step, it makes the
    // locally optimal choice by selecting the smallest available card that can be used to start a new group.
    // By doing this at each step, it aims to achieve the overall optimal solution.
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i: hand) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        while(!freqMap.isEmpty()) {
            Integer card = freqMap.firstKey();
            for (int i = 1; i < groupSize; i++) {
                int nextCard = card + i;
                if (!freqMap.containsKey(nextCard)) {
                    return false;
                }

                freqMap.put(nextCard, freqMap.get(nextCard) - 1);
                if (freqMap.get(nextCard) == 0) {
                    freqMap.remove(nextCard);
                }
            }

            freqMap.put(card, freqMap.get(card) - 1);
            if (freqMap.get(card) == 0) {
                freqMap.remove(card);
            }
        }

        return true;
    }

    // almost the same, but we include first card in a group cycle
    // so no need of double-checking of a first card
    public static boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i: hand) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        while(!freqMap.isEmpty()) {
            // we include first card in a group cycle,
            // so no need to separately check its count
            Integer card = freqMap.firstKey();
            for (int i = 0; i < groupSize; i++) {
                int nextCard = card + i;
                if (!freqMap.containsKey(nextCard)) {
                    return false;
                }

                freqMap.put(nextCard, freqMap.get(nextCard) - 1);
                if (freqMap.get(nextCard) == 0) {
                    freqMap.remove(nextCard);
                }
            }
        }

        return true;
    }
}
