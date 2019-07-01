package chap02;

import java.util.Scanner;
import java.util.stream.IntStream;

public class MyBinarySearch {

    public static void main(String[] args) {
        System.out.println("Enter int to find in (0; 50)");
        Scanner sc = new Scanner(System.in);
        int valueToFind = sc.nextInt();
        /*try {
            System.out.println(MyBinarySearch.binarySearch(MyBinarySearch.getArray(), valueToFind));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //System.out.println(MyBinarySearch.find(42, MyBinarySearch.getArray()));
        System.out.println(MyBinarySearch.quick(MyBinarySearch.getArray(), valueToFind));
    }

    private static int[] getArray() {
        return IntStream.rangeClosed(1, 50).toArray();
    }

    /**
     * tl;dr - point to the middle of a range.
     * if value in it is bigger (than what you're looking for) - start looking at an upper half,
     * if less - than in a lower half.
     * and so on
     */
    private static String binarySearch(int[] array, int valToFind) throws InterruptedException {
        int startInd = 0;
        int endInd = array.length - 1;
        int curInd = endInd / 2;

        System.out.println("Starting search for value: " + valToFind + "\n");
        while (startInd < endInd) {
            curInd = (endInd + startInd) / 2;
            System.out.println("Search in range: " + startInd + "; " + endInd + " (middle index: " + curInd + ")");
            int curVal = array[curInd];
            if (curVal == valToFind) {
                return "Got it at index: " + curInd;
            } else if (curVal > valToFind) { // value in the middle is bigger
                System.out.println("Found value: " + curVal);
                System.out.println(valToFind + " is less than " + curVal + " (go to lower half)");

                // middle of current range becomes an upper bound of new range (don't forger it!)
                endInd = curInd - 1;

                System.out.println("Now searching in range: " + startInd + "; " + endInd
                        + "\n" + "________________________________");
            } else { // value in the middle is less
                System.out.println("Found value: " + curVal);
                System.out.println(valToFind + " is bigger than " + curVal + " (go to upper half)");

                // middle of current range becomes an lower bound of new range (don't forger it!)
                startInd = curInd + 1;

                System.out.println("Now searching in range: " + startInd + "; " + endInd
                        + "\n" + "________________________________");
            }
            Thread.sleep(1000);
        }
        return "Not found";
    }

    /**
     * from the book
     */
    private static int find(long searchKey, int[] a) {
        int nElems = a.length;
        int startInd = 0;
        int endInd = nElems - 1;
        int curInd;
        while (true) {
            System.out.println("range: " + startInd + "; " + endInd);
            curInd = (startInd + endInd) / 2;
            System.out.println("curInd: " + curInd);
            if (a[curInd] == searchKey) {
                System.out.println("got it at ind " + curInd);
                return curInd; // Элемент найден
            } else if (startInd > endInd) {
                return nElems; // Элемент не найден
            } else if (a[curInd] < searchKey) {
                System.out.println("В верхней половине");
                startInd = curInd + 1; // В верхней половине
            } else {
                System.out.println("В нижней половине");
                endInd = curInd - 1; // В нижней половине
            }
            System.out.println("________");
        }
    }


    /**
     * Check if I remembered
     */
    private static String quick(int[]a, int find) {
        int start = 0;
        int end = a.length - 1;
        while (start < end) {
            int curInd = (start + end) / 2;
            if (a[curInd] == find) {
                return "got it at: " + curInd;
            } else if (a[curInd] > find) {
                end = curInd - 1;
            } else {
                start = curInd + 1;
            }
        }
        return "not found";
    }
}
