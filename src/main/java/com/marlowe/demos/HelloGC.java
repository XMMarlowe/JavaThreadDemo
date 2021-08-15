package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description:
 * @author: Marlowe
 * @create: 2021-08-15 16:37
 **/
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        // 返回Java虚拟机中内存的总量
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 返回Java虚拟机中试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println(String.format("TOTAL_MEMORY(-Xms): %d B, %.2f MB.", totalMemory, totalMemory / 1024.0 / 1024));
        System.out.println(String.format("MAX_MEMORY(-Xmx): %d B, %.2f MB.", maxMemory, maxMemory / 1024.0 / 1024));
    }
}
