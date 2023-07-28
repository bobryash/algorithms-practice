package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #659. Encode and Decode Strings
 * <p>
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * <p>
 * Please implement encode and decode.
 * <p>
 * Example1
 * Input: ["lint","code","love","you"]
 * Output: ["lint","code","love","you"]
 * Explanation:
 * One possible encode method is: "lint:;code:;love:;you"
 * <p>
 * Example2
 * Input: ["we", "say", ":", "yes"]
 * Output: ["we", "say", ":", "yes"]
 * Explanation:
 * One possible encode method is: "we:;say:;:::;yes"
 */
public class EncodeAndDecodeStrings {

    public static void main(String[] args) {
        String encoded = encode(new ArrayList<>(Arrays.asList("lint", "code", "loves", "you")));
        System.out.println("Encoded: " + encoded);

        List<String> result = decode(encoded);
        System.out.print("Decoded: ");
        for (String s : result) {
            System.out.print(s + " ");
        }
    }

    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append("#").append(s);
        }

        // 4#lint4#code5#loves3#you
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public static List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < str.length()) {
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            result.add(str.substring(j + 1, j + length + 1));
            i = j + length + 1;
            j = i;
        }

        return result;
    }
}
