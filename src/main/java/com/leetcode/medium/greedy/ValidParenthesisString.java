package com.leetcode.medium.greedy;

import java.util.Stack;

/**
 * #678. Valid Parenthesis String
 *
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "(*)"
 * Output: true
 *
 * Example 3:
 * Input: s = "(*))"
 * Output: true
 */
public class ValidParenthesisString {

    public static void main(String[] args) {
        System.out.println(checkValidString("()")); // true
        System.out.println(checkValidString("(*)")); // true
        System.out.println(checkValidString("(*))")); // true
        System.out.println(checkValidString("(*)(")); // false
    }

    // the idea - use 2 stacks to track '(' and '*'.
    // * can be used as '(', ')' or '*', so we can use star,
    // if we have nothing in open-stack at the moment, as a substitution
    public static boolean checkValidString(String s) {
        Stack<Integer> open = new Stack<>(); // to store indexes of (
        Stack<Integer> star = new Stack<>(); // to store indexes of *

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') { // just push ( index in open
                open.push(i);
            } else if (c == '*') { // just push * index in star
                star.push(i);
            } else { // closing ')' - time to pop
                if (!open.isEmpty()) { // pop corresponding open ( as normal
                    open.pop();
                } else if (!star.isEmpty()) { // no open (, so use star as substitution
                    star.pop();
                } else { // no open (, and no stars, so string is invalid
                    return false;
                }
            }
        }

        // process leftovers, e.g. might be a lot of stars in the end (***
        while (!open.isEmpty() && !star.isEmpty()) {
            if (open.peek() < star.peek()) {
                open.pop();
                star.pop();
            } else {
                // string is invalid, if open is farther than star: *(
                break;
            }
        }
        // after the loop it'll be ** - open is empty, and ** can be ()/__, so it'll be valid

        return open.isEmpty();
    }
}
