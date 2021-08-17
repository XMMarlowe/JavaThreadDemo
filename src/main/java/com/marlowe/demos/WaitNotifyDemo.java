package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description: bject类中的wait和notify方法实现线程等待和唤醒
 * @author: Marlowe
 * @create: 2021-08-17 17:09
 **/
public class WaitNotifyDemo {
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + " 通知.");
            }
        }, "B").start();
    }
}
