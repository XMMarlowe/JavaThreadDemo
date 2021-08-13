package com.marlowe.demos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaThreadDemo
 * @description: 锁绑定等多个条件Condition
 * 多线程之间按顺序调用，实现 A-> B -> C 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * …
 * 来3轮
 * @author: Marlowe
 * @create: 2021-08-13 14:11
 **/
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        int num = 3;
        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < num; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}


class ShareResource {
    /**
     * A:1  B:2 C:3
     */
    private int number = 1;

    /**
     * 创建一个可重入如锁
     */
    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                // 不等于1，需要等待
                condition1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number + "\t" + i);
            }
            // 唤醒 （干完活后，需要通知C线程执行）
            number = 2;
            // 通知2号去干活了
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                // 不等于2，需要等待
                condition2.await();
            }
            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number + "\t" + i);
            }
            // 唤醒 （干完活后，需要通知C线程执行）
            number = 3;
            // 通知3号去干活了
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print15() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                // 不等于3，需要等待
                condition3.await();
            }
            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number + "\t" + i);
            }
            // 唤醒 （干完活后，需要通知C线程执行）
            number = 1;
            // 通知1号去干活了
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}