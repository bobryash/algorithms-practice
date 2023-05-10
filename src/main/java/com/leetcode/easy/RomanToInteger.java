package com.leetcode.easy;

import java.util.HashMap;

/**
 * 13. Given a roman numeral, convert it to an integer.
 */
public class RomanToInteger {

    /**
     * The idea - roman literals are written from bigger numbers to less: XVIII
     * But there's a few exceptions, when they are switched - that means that you should subtract left from right (no matter what they are): XIX, IV
     * Take the far right as a result start, then make a cycle for the rest.
     * Compare it with the last right and so on
     */
    public static void main(String[] args) {
        System.out.println(1994 == romanToInt("MCMXCIV"));
        System.out.println(58 == romanToInt("LVIII"));
        System.out.println(9 == romanToInt("IX"));
    }

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return -1;

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int len = s.length();
        int result = map.get(s.charAt(len - 1));

        // in reverse - if next one is bigger (1 < 5) then subtract; if next one is lesser (100 > 1) - then sum
        // MCMXCIV = 1000/100/1000/10/100/1/5  = 5 - 1 + 100 - 10 + 1000 - 100 - 1000 = 1994 (backward)
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }
        return result;
    }
}
