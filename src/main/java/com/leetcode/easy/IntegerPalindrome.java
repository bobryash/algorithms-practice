package com.leetcode.easy;

import java.util.Arrays;

/**
 * Examples:
 * 121, 1221, 735537 - true
 * 123, 1334, -121 - false
 */
public class IntegerPalindrome {

    public static void main(String[] args) {
        new IntegerPalindrome().isPalindrome(123321);
    }

    // 8ms runtime, 40mb memory. for '121'
    public boolean isPalindromeBruteForce(int x) {
        char[] arr = Integer.toString(x).toCharArray();
        char[] backwardArr = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            backwardArr[i] = arr[arr.length - i - 1];
        }
        return Arrays.equals(arr, backwardArr);
    }

    /**
     * Идея - разделить число пополам, развернуть вторую половину наоборот и сравнить с первой половиной
     * 123321 -> 123 321 -> 123 == 123
     * Плюсы - страхует от оверфлоу (если перевернуть все число сразу, то оно может быть больше Integer.MAX)
     */
    public boolean isPalindrome(int x) {
        // if int ends with 0 and it's not a zero, then it cannot be palindrome
        // example: 120, 12310
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // когда revertedNumber больше OG, значит перешли за половину
        while (x > revertedNumber) {
            /*
                modulus division example: 21 % 8 = 5 (leftover after deletion 20 by 8 evenly(нацело) 21 - 8 - 8 = 5)
                деля OG число на 10 по модулю получаем последнюю цифру (сохраняем в revertedNumber): 1221 % 10 = 1
                просто делим OG число на 10, чтобы отсечь последнюю цифру: 1221/10 = 122
                опять отсекаем последнюю цифру у OG числа: 122%10 = 2
                умножаем revertedNumber на 10 (чтобы было куда прибавлять) и прибавляем предыдущую последнюю цифру:
                revertedNumber * 10 + 122 % 10
            */
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
            System.out.println(x + " - " + revertedNumber);
        }

        // если длина нечетная, то можно отбросить середину (для палиндрома не важно). если четная, то половины равны (по длине)
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
