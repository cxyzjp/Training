package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序。
 * 从未排序的数字中，选择一个最小的排在已排序的最后一个
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = NumberGenerator.toSortNumber();
        solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

}
