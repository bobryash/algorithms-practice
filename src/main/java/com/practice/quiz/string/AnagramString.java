package com.practice.quiz.string;

import java.util.Arrays;

public class AnagramString {

    public static boolean iAnagram(String word, String anagram) {
        char[] charFromWord = word.toCharArray();
        char[] charFromAnagram = anagram.toCharArray();
        Arrays.sort(charFromWord);
        Arrays.sort(charFromAnagram);
        return Arrays.equals(charFromWord, charFromAnagram);
    }

    public static void main(String[] args) {
        String dog = "dog";
        String god = "god";
        System.out.println(iAnagram(dog, god));
    }
}
