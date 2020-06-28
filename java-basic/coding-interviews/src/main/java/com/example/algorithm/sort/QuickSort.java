package com.example.algorithm.sort;

import static com.example.utils.ArrayUtils.*;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = randomArray();
        solution(arr);
        printArrays(arr);

        arr = randomArray();
        sort2(arr, 0, arr.length - 1);
        printArrays(arr);
    }

    public static void solution(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int baseIndex = startIndex;
        int baseValue = arr[startIndex];
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < baseValue) {
                baseIndex++;
                if (i > baseIndex) {
                    swap(arr, i, baseIndex);
                }
            }
        }
        arr[startIndex] = arr[baseIndex];
        arr[baseIndex] = baseValue;

        sort(arr, startIndex, baseIndex - 1);
        sort(arr, baseIndex + 1, endIndex);
    }

    public static void sort2(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int left = startIndex;
        int right = endIndex;
        int baseValue = arr[startIndex];
        while (left < right) {
            while (arr[right] > baseValue && left < right) {
                right--;
            }
            while (arr[left] <= baseValue && left < right) {
                left++;
            }

            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, startIndex, left);

        sort2(arr, startIndex, left - 1);
        sort2(arr, left + 1, endIndex);
    }

}
