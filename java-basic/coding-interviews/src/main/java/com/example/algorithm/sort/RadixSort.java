package com.example.algorithm.sort;

import com.example.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = ArrayUtils.randomArray(10, 1, 9999);
        sort(arr);
        ArrayUtils.printArrays(arr);
    }

    public static void sort(int[] arr) {
        int max = arr[0];
        for (int item : arr) {
            if (item > max) {
                max = item;
            }
        }

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }

        int location = 1;
        while (true) {
            int pow = (int) Math.pow(10, location - 1);
            if (pow > max) {
                break;
            }
            for (int value : arr) {
                int num = (value / pow) % 10;
                buckets.get(num).add(value);
            }
            int index = 0;
            for (List<Integer> bucket : buckets) {
                for (Integer integer : bucket) {
                    arr[index++] = integer;
                }
                bucket.clear();
            }
            location++;
        }
    }
}
