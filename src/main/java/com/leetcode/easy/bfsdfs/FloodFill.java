package com.leetcode.easy.bfsdfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * #733
 * <p>
 * An image is represented by an m X n integer grid image where image[i][j] represents the pixel value of the image.
 * <p>
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the
 * pixel image[sr][sc].
 * <p>
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting
 * pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 * <p>
 * Return the modified image after performing the flood fill.
 */
public class FloodFill {

    public static void main(String[] args) {
        // image:
        // 1 1 1
        // 1 1 0
        // 1 0 1
        //
        // 1, 1 - given position
        //
        // should become
        // 2 2 2
        // 2 2 0
        // 2 0 1
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] result = floodFill(image, 1, 1, 2);
        Arrays.stream(result).forEach(a -> {
            System.out.println(Arrays.toString(a));
        });
    }

    // with queue (better imo)
    /**
     *
     * @param image original image
     * @param sr start row
     * @param sc start column
     * @param newColor new color integer value
     * @return flood filled image
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Check if the new and old colors are the same
        if (image[sr][sc] == newColor) {
            return image;
        }

        // Initialize the queue and starting color
        Queue<int[]> queue = new LinkedList<>();
        int oldColor = image[sr][sc];

        // Add the seed point to the queue
        queue.add(new int[] { sr, sc });

        // Run the fill function starting at the position given
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];

            // Check if the current point is outside the image bounds
            if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
                continue;
            }

            // Check if the current point has the same color as the starting color
            if (image[row][col] != oldColor) {
                continue;
            }

            // Update the image[row][col] as a new color
            image[row][col] = newColor;

            // Add the surrounding pixels to the queue
            queue.add(new int[] { row - 1, col });
            queue.add(new int[] { row + 1, col });
            queue.add(new int[] { row, col - 1 });
            queue.add(new int[] { row, col + 1 });
        }

        return image;
    }


    // with recursion
    public static int[][] floodFill1(int[][] image, int sr, int sc, int color) {
        // Avoid infinite loop if the new and old colors are the same
        if (image[sr][sc] == color) {
            return image;
        }

        // Run the fill function starting at the position given
        fill(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    public static void fill(int[][] image, int sr, int sc, int color, int cur) {
        // If sr is less than 0 or greater equals to the length of image...
        // Or, If sc is less than 0 or greater equals to the length of image[0]...
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return;
        }

        // If image[sr][sc] is not equal to previous color...
        if (cur != image[sr][sc]) {
            return;
        }

        // Update the image[sr][sc] as a color...
        image[sr][sc] = color;
        // Make four recursive calls to the function with (sr-1, sc), (sr+1, sc), (sr, sc-1) and (sr, sc+1)...
        fill(image, sr - 1, sc, color, cur);
        fill(image, sr + 1, sc, color, cur);
        fill(image, sr, sc - 1, color, cur);
        fill(image, sr, sc + 1, color, cur);
    }
}
