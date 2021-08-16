package com.marlowe.demos;

/**
 * @program: JavaThreadDemo
 * @description:
 * GC回收时间过长时会抛出OutOfMemoryError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存，
 * 连续多次GC 都只回收了不到2%的极端情况下才会抛出。
 * 假如不抛出GC overhead limit错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会再次填满，迫使cc再次执行。这样就形成恶性循环，CPU使用率一直是100%，而Gc却没有任何成果。
 * @author: Marlowe
 * @create: 2021-08-16 09:53
 **/
public class GCOverheadDemo {
}
