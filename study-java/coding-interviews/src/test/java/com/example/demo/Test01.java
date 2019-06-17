package com.example.demo;

import org.junit.Test;

/**
 * description:
 * author: bowen
 * date: 2019/6/17
 */
public class Test01 extends BaseTest {

    @Test
    public void test01() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        int target = 4;
        int[][] array = {{1, 3, 5}, {2, 4, 8}, {6, 7, 9}};
        int[][] a = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};

        Test01 test01 = new Test01();
        System.out.println(test01.Find3(5, a));
    }

    public boolean Find(int target, int[][] array) {
        for (int[] arr : array) {
            for (int i : arr) {
                if (i == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Find2(int target, int[][] array) {
        int col = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            int[] arr = array[i];
            for (; col < arr.length; col++) {
                int t = arr[col];
                if (t == target) {
                    return true;
                }
                if (target < t) {
                    break;
                }
            }
        }
        return false;
    }

    public boolean Find3(int target, int[][] array) {
        int row = array.length - 1;
        int col = 0;
        while (col < array[0].length && row >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if (target < array[row][col]) {
                row--;
            } else if (target > array[row][col]) {
                col++;
            }
        }
        return false;
    }
}
