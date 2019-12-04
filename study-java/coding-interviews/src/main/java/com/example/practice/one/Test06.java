package com.example.practice.one;

/**
 * description:
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 * author: bowen
 * date: 2019/6/19
 */
public class Test06 {

    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] > array[i]) {
                return array[i];
            }
        }
        return array[0];
    }

    public int minNumberInRotateArray2(int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > array[high]) {
                low = mid + 1;
            } else if (array[mid] == array[high]) {
                high = high - 1;
            } else {
                high = mid;
            }
        }
        return array[low];
    }

    public static void main(String[] args) {
        Test06 t = new Test06();
        int[] array = {3, 4, 5, 1, 2};
        int[] a2 = {1, 1, 1, 0};
        System.out.println(t.minNumberInRotateArray(array));
        System.out.println(t.minNumberInRotateArray2(a2));
    }
}
