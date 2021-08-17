package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description: 可重入锁
 * @author: Marlowe
 * @create: 2021-08-17 16:52
 **/
public class ReentrantLockDemo2 {

    public static void main(String[] args) {
        new ReentrantLockDemo2().m1();

    }

    public synchronized void m1() {
        System.out.println("===外");
        m2();
    }

    public synchronized void m2() {
        System.out.println("===中");
        m3();
    }

    public synchronized void m3() {
        System.out.println("===内");

    }
}

