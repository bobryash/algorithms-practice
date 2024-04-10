package com.leetcode.medium.backtracking;

/**
 * #79. Word Search
 * <p>
 * Given an m x n grid of characters board and a string word,
 * return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Example 1:
 * A B C E
 * S F C S
 * A D E E
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * <p>
 * Example 2:
 * A B C E
 * S F C S
 * A D E E
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * <p>
 * Example 3:
 * A B C E
 * S F C S
 * A D E E
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 */
public class WordSearch {

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED")); // true
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE")); // true
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB")); // false
    }

    public static boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // backtrack will brut-force every combination in a grid
                if (backtrack(n, m, i, j, board, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean backtrack(int n, int m, int i, int j, char[][] board, String word, int start) {
        if (start >= word.length()) { // base case - we went through and found each letter in a word
            return true;
        }

        // if we went out of boundaries, or if letter is not a match
        if (i < 0 ||
                i >= n ||
                j < 0 ||
                j >= m ||
                board[i][j] != word.charAt(start)) {
            return false;
        }

        boolean exist = false;
        // if found a match, let's find next letters
        if (board[i][j] == word.charAt(start)) {
            // change the char for smth out of ASCII letters, so other paths won't count it second time
            board[i][j] += 100;
            // let's try to find match for next letter in all directions from current match
            exist = backtrack(n, m, i + 1, j, board, word, start + 1) || // right
                    backtrack(n, m, i - 1, j, board, word, start + 1) || // left
                    backtrack(n, m, i, j + 1, board, word, start + 1) || // up
                    backtrack(n, m, i, j - 1, board, word, start + 1); // down
            board[i][j] -= 100; // get letter back to original value
        }

        return exist;
    }
}
