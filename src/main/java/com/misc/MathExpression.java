package com.misc;

/**
 * Evaluate math expression put in string.
 * Only + and * operations in it.
 */
public class MathExpression {

    public static void main(String[] args) {
        String expr = "";
        System.out.println(calcWithExtraSpace(expr)); // -1
        System.out.println(calcNoExtraSpace(expr));

        expr = "1";
        System.out.println(calcWithExtraSpace(expr)); // 1
        System.out.println(calcNoExtraSpace(expr));

        expr = "1+2";
        System.out.println(calcWithExtraSpace(expr)); // 3
        System.out.println(calcNoExtraSpace(expr));

        expr = "23";
        System.out.println(calcWithExtraSpace(expr)); // 23
        System.out.println(calcNoExtraSpace(expr));

        expr = "1+23+5";
        System.out.println(calcWithExtraSpace(expr)); // 29
        System.out.println(calcNoExtraSpace(expr));

        expr = "1+2*3*4+5*2";
        System.out.println(calcWithExtraSpace(expr)); // 35
        System.out.println(calcNoExtraSpace(expr));
    }

    // 12+23*2*4+34+1+212*3 - contains only + and *
    public static int calcWithExtraSpace(String expr) {
        if (expr == null || expr.isEmpty()) return 0;

        String[] summands = expr.split("\\+"); // extra memory
        int sum = 0;

        for (String str : summands) {
            if (!str.contains("*")) {
                sum += Integer.parseInt(str);
            } else {
                int start = 0;
                int product = 1;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (c == '*') {
                        product *= Integer.parseInt(str.substring(start, i)); // i is not included
                        start = i + 1;
                    } else if (i + 1 == str.length()) {
                        product *= Integer.parseInt(str.substring(start)); // from start till the end of str
                    }
                }
                sum += product;
            }
        }

        return sum;
    }

    // 12+23*2*4+34+1+212*3 - contains only + and *
    public static int calcNoExtraSpace(String expr) {
        int result = 0;
        int currVal = 0;
        int currProduct = 1;
        boolean isMultiplying = false;

        for (char c: expr.toCharArray()) {
            if (c == '+') {
                result += isMultiplying ? currProduct * currVal : currVal;
                currVal = 0;
                currProduct = 1;
                isMultiplying = false;
            } else if (c == '*') {
                currProduct *= currVal;
                currVal = 0;
                isMultiplying = true;
            } else {
                currVal = currVal * 10 + Character.getNumericValue(c); // or c - '0', kind of as c - 'a' for letters
            }
        }

        // to handle last value
        result += isMultiplying ? currProduct * currVal : currVal;

        return result;
    }
}
