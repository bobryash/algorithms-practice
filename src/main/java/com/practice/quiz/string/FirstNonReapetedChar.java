package com.practice.quiz.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstNonReapetedChar {

    public static void main(String[] args) {
        System.out.println(firstNonRepeatingChar("adsad"));
    }

    /**
     * Finds first non repeated character in a String in just one pass. It uses two storage to cut down one
     * iteration, standard space vs time trade-off.Since we store repeated and non-repeated character separately,
     * at the end of iteration, first element from List is our first non repeated character from String.
     */
    public static char firstNonRepeatingChar(String word) {
        Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();
        char[] characters = word.toCharArray();
        for (char letter : characters) {
            if (repeating.contains(letter)) {
                continue;
            }
            if (nonRepeating.contains(letter)) {
                nonRepeating.remove((Character) letter);
                repeating.add(letter);
            } else {
                nonRepeating.add(letter);
            }
        }
        return nonRepeating.get(0);
    }

    public static char practice(String word) {
        return 'j';
    }
}
