package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description: 可重入锁
 * @author: Marlowe
 * @create: 2021-08-17 16:50
 **/
public class ReentrantLockDemo {
    Object object = new Object();

    public void synchronizedMethod() {
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t" + "外层....");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "中层....");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "内层....");
                    }
                }
            }
        }, "Thread A").start();
    }

    public static void main(String[] args) {
        new ReentrantLockDemo().synchronizedMethod();
    }

}

