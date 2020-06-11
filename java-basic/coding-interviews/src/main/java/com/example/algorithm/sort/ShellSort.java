package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序，插入排序升级
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = NumberGenerator.toSortNumber();
        solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr) {
        int length = arr.length;
        int step = length / 2;
        while (step > 0) {
            for (int i = step; i < length; i++) {
                int current = arr[i];
                int j = i;
                while (j - step >= 0 && arr[j - step] > current) {
                    arr[j] = arr[j - step];
                    j = j - step;
                }
                arr[j] = current;
            }
            step = step / 2;
        }
    }

}
