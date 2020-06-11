package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = NumberGenerator.toSortNumber();
        System.out.println(Arrays.toString(arr));
        solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void solution(int[] arr) {
        int length = arr.length;
        int[] temp = new int[length];
        sort(arr, temp, 0, length - 1);
    }

    private static void sort(int[] arr, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(arr, temp, start, mid);
        sort(arr, temp, mid + 1, end);

        System.arraycopy(arr, start, temp, start, end - start + 1);
        int left = start;
        int right = mid + 1;
        for (int i = start; i <= end; i++) {
            if (left > mid) {
                //如果左边的首位下标大于中部下标，证明左边的数据已经排完了。
                arr[i] = temp[right++];
            } else if (right > end) {
                //如果右边的首位下标大于了数组长度，证明右边的数据已经排完了。
                arr[i] = temp[left++];
            } else if (temp[right] < temp[left]) {
                //将右边的首位排入，然后右边的下标指针+1。
                arr[i] = temp[right++];
            } else {
                //将左边的首位排入，然后左边的下标指针+1。
                arr[i] = temp[left++];
            }
        }
    }

}
