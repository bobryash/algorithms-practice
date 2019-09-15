package com.practice.quiz;

public class PowerAndFactorial {
    public static void main(String[] args) {
        System.out.println(factorial(4));
        System.out.println(pow(2, 3));
    }

    // my version
    static int factorial(int number) {
        int result = number;
        for (int i = 1; i < number; i++) {
            int currentValue = number - i;
            result = result * currentValue;
        }
        return result;
    }

    // from internet
    public static int fact(int number) {
        int result = 1;
        while (number != 0) {
            result = result * number;
            number--;
        }

        return result;
    }

    static int pow(int number, int pow) {
        int result = number;
        for (int i = 1; i < pow; i++) {
            result = result * number;
        }
        return result;
    }

}
