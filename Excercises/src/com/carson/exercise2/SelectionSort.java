package com.carson.exercise2;

/**
 * Created by Carson on 27/01/2017.
 */
public class SelectionSort {

    public static final int MAX_SIZE = 100000;
    private static int[] nums = new int[MAX_SIZE];

    public static void main(String[] args) {
        // System.out.println("Please enter " + MAX_SIZE + " integers, in any order:");
        for (int i = 0; i < MAX_SIZE; i++) {
            nums[i] = (int) (Math.random() * MAX_SIZE);
        }
        long startTime = System.currentTimeMillis();
        selectionSort();
        int a = Search.binary(5000, nums, MAX_SIZE);
        int b = Search.sequential(5000, nums, MAX_SIZE);
        System.out.println("Program time: " + (System.currentTimeMillis() - startTime) + " ms");
        // printNums();
    }

    private static void selectionSort() {
        int endOfSortedRegion;
        int minLocation;
        for (endOfSortedRegion = 0; endOfSortedRegion < MAX_SIZE - 1; endOfSortedRegion++) {
            minLocation = endOfSortedRegion;
            for (int j = endOfSortedRegion + 1; j < MAX_SIZE; j++)

                if (nums[j] < nums[minLocation]) {
                    minLocation = j;
                }
            if (minLocation != endOfSortedRegion) {
                swap(minLocation, endOfSortedRegion);
            }
        }
    }

    private static void swap(int index1, int index2) {
        int temp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = temp;
    }

    private static void printNums() {
        for (int i = 0; i < MAX_SIZE; i++) {
            System.out.println(nums[i]);
        }
    }
}
