package com.example.algorithm.structure;

import static com.example.utils.ArrayUtils.*;

/**
 * 堆特性
 * 1. 堆是完全二叉树
 * 2. 堆顶是最大(最小)的值，父节点大于(小于)子节点
 * 3. 假设节点下标为n，则左节点为2n+1，右节点为2n+2，父节点为(n-1)/2
 * 4. 假设n为数组长度，数组下标超过n/2的都是叶子结点
 */
public class HeapOfArray {

    public static void main(String[] args) {
        int[] arr = randomArray();
        maxHeap(arr);
        printArrays(arr);

        for (int i = 0; i < 100; i++) {
            arr = randomArray();
            maxHeap(arr);
            printArrays(arr);
        }
        System.out.println("success");
    }

    public static void maxHeap(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int lastIndex = arr.length - 1;
        int lastRoot = (lastIndex - 1) / 2;
        for (int i = lastRoot; i >= 0; i--) {
            int tmpIndex = i;

            while (tmpIndex <= lastIndex) {
                int childIndex = tmpIndex * 2 + 1;
                if (childIndex < lastIndex && arr[childIndex] < arr[childIndex + 1]) {
                    childIndex++;
                }
                if (childIndex <= lastIndex && arr[childIndex] > arr[tmpIndex]) {
                    swap(arr, tmpIndex, childIndex);
                    tmpIndex = childIndex;
                } else {
                    break;
                }
            }
        }
    }

}
