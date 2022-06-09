package com.practice.quiz;

public class PowerAndFactorial {
    public static void main(String[] args) {
        System.out.println(factorial(4));
        System.out.println(power(2, 3));
    }

    private static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return (n * factorial(n - 1));
        }
    }

    private static int power(int number, int power) {
        int result = number;

        for (int i = 1; i < power; i++) {
            result = result * number;
        }

        return result;
    }
}
