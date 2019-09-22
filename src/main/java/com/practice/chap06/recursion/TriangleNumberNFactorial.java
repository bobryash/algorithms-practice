package com.practice.chap06.recursion;

/**
 * n-е треугольное число = (2n + n)/2
 * n - порядоквый номер
 */
public class TriangleNumberNFactorial {

    public static void main(String[] args) {
        System.out.println(TriangleNumberNFactorial.triangleCycle(5));
        System.out.println(TriangleNumberNFactorial.triangleRecursion(5));
        System.out.println(TriangleNumberNFactorial.factorial(3));
    }

    // цикл
    static int triangleCycle(int n) {
        int total = 0;
        while (n > 0) {// Пока n равно 1 и более
            total = total + n; // Переменная total увеличивается на n (высоту столбца)
            --n; // Уменьшение высоты столбца
        }
        return total;
    }

    static int triangleRecursion(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (n + triangleRecursion(n - 1));
        }
    }

    static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
