package com.example.everyday.m04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #42
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class T04 {

    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int pi = 0, pv = 0, sum = 0, tmp = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] == 0) {
                continue;
            }
            pi = i;
            pv = height[i];
            break;
        }
        if (pi == height.length - 1) {
            return 0;
        }

        for (int ni = pi + 1; ni < height.length; ni++) {
            int nv = height[ni];
            if (nv < pv) {    // 当前值小于水槽开始值，tmp累加
                tmp += pv - nv;
                if(ni == height.length - 1){ // 最后一个值小于水槽开始值，重新计算最后一个槽的存水量
                    int[] lastArr = new int[ni - pi + 1];
                    for(int i = ni;i>=pi;i--){
                        lastArr[ni-i] = height[i];
                    }
                    sum += trap(lastArr);
                }
            } else {         // 当前值等于或大于水槽开始值
                if (ni - pi > 1) {// 非相邻时，累加该次存水量
                    sum += tmp;
                    tmp = 0;
                }
                pi = ni;
                pv = nv;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        T04 t = new T04();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(t.trap(height));
    }
}
