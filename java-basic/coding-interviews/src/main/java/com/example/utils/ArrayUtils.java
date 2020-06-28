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
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random(1, 10);
        }
        printArrays(arr);
        return arr;
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
