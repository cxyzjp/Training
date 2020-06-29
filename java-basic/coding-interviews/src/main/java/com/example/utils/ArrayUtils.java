package com.example.utils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtils {

    public static void swap(int[] arr, int i, int j) {
        int maxIndex = arr.length - 1;
        if (i < 0 || j < 0 || i > maxIndex || j > maxIndex) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArrays(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static int[] randomArray() {
        return randomArray(10, 1, 10);
    }

    public static int[] randomArray(int length) {
        return randomArray(length, 1, 10);
    }

    public static int[] randomArray(int length, int min, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random(min, max);
        }
        printArrays(arr);
        return arr;
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
