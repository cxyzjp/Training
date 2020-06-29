package com.example.algorithm.sort;

import com.example.algorithm.structure.HeapOfArray;

import static com.example.utils.ArrayUtils.*;

public class HeapSort {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] arr = randomArray();
            sort(arr);
            printArrays(arr);

            arr = randomArray(9);
            sort(arr);
            printArrays(arr);
        }
    }

    public static void sort(int[] arr) {
        HeapOfArray.maxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            HeapOfArray.sink(arr, 0, i);
        }
    }
}
