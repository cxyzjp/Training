package com.example.practice.one;

/**
 * description: 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * <p>
 * author: bowen
 * date: 2019/6/17
 */
public class Test02 {
    public static void main(String[] args) {
        Test02 t = new Test02();

    }

    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ", "%20");
    }
}
