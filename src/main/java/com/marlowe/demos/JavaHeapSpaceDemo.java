package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description: OOM之Java Heap Space
 * @author: Marlowe
 * @create: 2021-08-16 09:37
 **/
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[15 * 1024 * 1024];
    }
}
