package com.marlowe.demos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: JavaThreadDemo
 * @description: Callable接口
 * @author: Marlowe
 * @create: 2021-08-13 15:46
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask, "A").start();
        //多个线程执行 一个FutureTask的时候，只会计算一次
        new Thread(futureTask, "B").start();

        // 输出FutureTask的返回值
        System.out.println("result FutureTask " + futureTask.get());
    }
}


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("******come in callable");
        return 1024;
    }
}