package com.marlowe.demos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: JavaThreadDemo
 * @description:
 * @author: Marlowe
 * @create: 2021-08-17 17:27
 **/
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t" + "-----被唤醒");
        }, "Thread A");
        a.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "-----通知了");
        }, "Thread B");
        b.start();


    }
}
