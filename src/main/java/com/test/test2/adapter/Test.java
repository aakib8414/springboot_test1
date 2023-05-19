package com.test.test2.adapter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
// 2D array, 2 1d array
//      1 2 3
    /*  1 2 3  1
        4 5 6  2
        7 8 9  3
        Ans :  6 15 24
     */
        int[][] arr = new int[3][3];
        final int[] val = new int[1];

        IntStream.range(0, 3).forEach(i -> {
            IntStream.range(0, 3).forEach(j -> {
                arr[i][j] = ++val[0];
            });
        });
        System.out.print(Arrays.deepToString(arr));
        int[] d = new int[arr.length];

        IntStream.range(0, 3).forEach(i -> {
            final int[] sum = new int[1];
            IntStream.range(0, 3).forEach(j -> {
                sum[0] = sum[0] + arr[i][j];
            });
            d[i] = sum[0];
        });
//        System.out.println("\nRes returned to 1D: " + Arrays.toString(d));

//        int[][] twoDArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[] flattenedArray = Arrays.stream(twoDArray)
//                .flatMapToInt(Arrays::stream)
//                .toArray();
//        System.out.println("\nRes returned to 1D: " + Arrays.toString(flattenedArray));
    }
}
