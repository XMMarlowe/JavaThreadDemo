package com.marlowe.demos;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaThreadDemo
 * @description: 死锁编码以及定位分析
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种相互等待的现象，
 * 若无外力干涉那他们都无法推进下去
 *
 * 解决方案：
 * 1. jps命令定位进程号        jps -l
 * 2. jstack找到死锁查看      jstack ”进程号“ --> Found 1 deadlock.
 * @author: Marlowe
 * @create: 2021-08-15 09:39
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();
    }
}


class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + "\t 尝试获得：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockB + "\t 尝试获得：" + lockA);
            }
        }
    }
}