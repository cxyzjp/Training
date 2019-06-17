package com.example.demo;

/**
 * description:
 * author: bowen
 * date: 2019/6/17
 */
public class Test02 extends BaseTest {
    public static void main(String[] args) {
        Test02 t = new Test02();

    }

    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ", "%20");
    }
}
