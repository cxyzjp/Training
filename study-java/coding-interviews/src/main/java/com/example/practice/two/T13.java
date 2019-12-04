package com.example.practice.two;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class T13 {
    public static void main(String[] args) {
        T13 t = new T13();

        // 1,2,3,4,5
        // 1,3,5,2,4
        int[] a = new int[]{2, 2, 3, 4, 5};
        t.reOrderArray(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 冒泡思路
     */
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                int j = i;
                while (j > 0) {
                    if (array[j - 1] % 2 == 0) {
                        int temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                        j--;
                    } else {
                        break;
                    }
                }
            }

        }
    }
}
