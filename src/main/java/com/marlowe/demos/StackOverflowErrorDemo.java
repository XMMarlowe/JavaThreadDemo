package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description: StackOverflowError
 * @author: Marlowe
 * @create: 2021-08-16 09:32
 **/
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    public static void stackOverflowError() {
        stackOverflowError();
    }
}
