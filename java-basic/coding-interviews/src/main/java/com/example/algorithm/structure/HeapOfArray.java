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

        arr = randomArray();
        maxHeapRecursive(arr);
        printArrays(arr);

        arr = randomArray(9);
        maxHeapRecursive(arr);
        printArrays(arr);
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

    public static void maxHeapRecursive(int[] arr) {
        int lastNotLeaf = (arr.length - 1) / 2;
        for (int i = lastNotLeaf; i >= 0; i--) {
            sink(arr, i, arr.length);
        }
    }

    /**
     * 递归方式
     *
     * @param arr    数组
     * @param index  要下沉的下标
     * @param length 要调整的数组范围
     */
    public static void sink(int[] arr, int index, int length) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int present = index;

        if (leftIndex < length && arr[leftIndex] > arr[present]) {
            present = leftIndex;
        }
        if (rightIndex < length && arr[rightIndex] > arr[present]) {
            present = rightIndex;
        }
        if (present != index) {
            swap(arr, present, index);
            sink(arr, present, length);
        }
    }

}
