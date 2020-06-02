package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序。
 * 如果从小到大排序。比较相邻2个数字，前一个比后一个大就交换值。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = NumberGenerator.toSortNumber();
        solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

}
