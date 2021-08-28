package com.marlowe.demos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaThreadDemo
 * @description: 线程通信之生产者消费者传统版
 * @author: Marlowe
 * @create: 2021-08-28 10:46
 **/
public class TraditionalProducerConsumerDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        // t1线程，生产
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        // t2线程，消费
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();

    }
}


class ShareData {

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        // 同步代码块，加锁
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                // 等待不能生产
                condition.await();
            }

            // 干活
            number++;

            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 通知 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        // 同步代码块，加锁
        lock.lock();
        try {
            // 判断
            while (number == 0) {
                // 等待不能消费
                condition.await();
            }

            // 干活
            number--;

            System.out.println(Thread.currentThread().getName() + "\t " + number);

            // 通知 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
