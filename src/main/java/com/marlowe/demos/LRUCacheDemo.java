package com.marlowe.demos;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: JavaThreadDemo
 * @description: LinkedHashMap实现LRU算法
 * @author: Marlowe
 * @create: 2021-08-20 14:18
 **/
public class LRUCacheDemo<K, V> extends LinkedHashMap {

    private int capacity;

    /**
     * initialCapacity – the initial capacity
     * loadFactor – the load factor
     * accessOrder – the ordering mode - true for access-order, false for insertion-order
     *
     * @param capacity
     */
    public LRUCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4, "d");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(5, "x");
        System.out.println(lruCacheDemo.keySet());

    }
}
