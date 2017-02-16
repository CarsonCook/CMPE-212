package com.carson.exercise2;

/**
 * Created by Carson on 27/01/2017.
 */
public class Search {
    public static int sequential(int target, int[] arr, int size) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            if (arr[i] == target) {
                System.out.println("Sequential took: " + (System.currentTimeMillis() - startTime) + " ms");
                return i;
            }
        }
        System.out.println("Sequential took: " + (System.currentTimeMillis() - startTime) + " ms");
        return -1;
    }

    public static int binary(int target, int[] arr, int size) {
        long startTime = System.currentTimeMillis();
        int low = -1;
        int high = size - 1;
        int mid = (high + low) / 2;
        while (low < high) {
            if (target > arr[mid]) {
                low = mid + 1;
            } else if (target < arr[mid]) {
                high = mid - 1;
            } else {
                System.out.println("Binary took: " + (System.currentTimeMillis() - startTime) + " ms");
                return mid;
            }
            mid = (low + high) / 2;
        }
        System.out.println("Binary took: " + (System.currentTimeMillis() - startTime) + " ms");
        return -1;
    }
}
