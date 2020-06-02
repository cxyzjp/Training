package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序排序。
 * 如果从小到大排序。选择未排序的第一个，依次比较已排序的数字，找到比自己小的，插到改数后面
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = NumberGenerator.toSortNumber();
        System.out.println(Arrays.toString(arr));
        solution2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
    }


    public static void solution2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int preIndex = i - 1;

            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

}
