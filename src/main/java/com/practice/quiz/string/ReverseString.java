package com.practice.quiz.string;

// 4
public class ReverseString {

    public static void main(String[] args) {
        String hello = "hello";
        String world = "world";


        //System.out.println(reverse(hello) + " " + reverseRecursively(world));
    }

    public static String reverse(String str) {
        StringBuilder strBuilder = new StringBuilder();
        char[] strChars = str.toCharArray();

        for (int i = strChars.length - 1; i >= 0; i--) {
            strBuilder.append(strChars[i]);
        }


        return strBuilder.toString();
    }

    public  String reverseRecursively(String str) {
        //base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }
        return reverseRecursively(str.substring(1)) + str.charAt(0);

    }

}
