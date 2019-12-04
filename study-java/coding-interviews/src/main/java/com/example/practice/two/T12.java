package com.example.practice.two;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 */
public class T12 {

    public static void main(String[] args) {
        T12 t = new T12();

        System.out.println(t.Power(2, -3));
    }

    public double Power(double base, int exponent) {
        if (base == 0 && exponent == 0) {
            return 0;
        }
        double r = base;
        int c = Math.abs(exponent);
        for (int i = 1; i < c; i++) {
            r *= base;
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / r;
        }
        return r;
    }

}
