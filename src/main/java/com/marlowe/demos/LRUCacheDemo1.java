package com.marlowe.demos;

import java.util.HashMap;
import java.util.Map;


/**
 * @program: JavaThreadDemo
 * @description: 链表+hash实现LRU算法
 * @author: Marlowe
 * @create: 2021-08-20 14:18
 **/
public class LRUCacheDemo1 {

    /**
     * map 负责查找，构建一个虚拟的双向链表，它里面装的就是一个个Node节点，作为数据载体
     */

    /**
     * 1.构造一个Node节点，作为数据载体
     *
     * @param <K>
     * @param <V>
     */
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        /**
         * 无参构造，初始化节点
         */
        public Node() {
            this.prev = this.next = null;
        }

        /**
         * 有参构造，初始化节点
         *
         * @param key
         * @param value
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    /**
     * 2.构建一个虚拟的双向链表,里面安放得就是我们的Node
     *
     * @param <K>
     * @param <V>
     */
    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        /**
         * 2.1构造方法，初始化双向链表
         */
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.next = head;
        }

        /**
         * 2.2添加到头
         *
         * @param node
         */
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        /**
         * 2.3 删除节点
         *
         * @param node
         */
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        /**
         * 2.4 获取最后一个节点
         *
         * @return
         */
        public Node getLast() {
            return tail.prev;
        }
    }

    /**
     * LRU容量
     */
    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    /**
     * 初始化LRU
     * @param cacheSize
     */
    public LRUCacheDemo1(int cacheSize) {
        this.cacheSize = cacheSize;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    /**
     * 向LRU中放值
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        // 如果map里面有key，更新value值，放回map，并移动到队首
        if (map.containsKey(key)) {
            Node<Integer, Integer> node = map.get(key);
            // 更新node的value
            node.value = value;
            // 更新node
            map.put(key, node);

            // 将当前节点移动到队首
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            if (map.size() == cacheSize) {
                // 如果map满了，map和双向链表都移除最后一个元素
                Node lastNode = doubleLinkedList.getLast();
                // map和链表都移除当前最后一个node
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            // 如果链表没有满，新建节点并从头部加入
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo1 lruCacheDemo = new LRUCacheDemo1(3);
        System.out.println("缓存容量:" + lruCacheDemo.cacheSize);
        lruCacheDemo.put(1, 1);
        System.out.println("map大小：" + lruCacheDemo.map.size());
        lruCacheDemo.put(2, 2);
        System.out.println("map大小：" + lruCacheDemo.map.size());
        lruCacheDemo.put(3, 3);
        System.out.println("map大小：" + lruCacheDemo.map.size());

        lruCacheDemo.put(4, 4);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("map大小：" + lruCacheDemo.map.size());

        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("map大小：" + lruCacheDemo.map.size());

        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("map大小：" + lruCacheDemo.map.size());

        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("map大小：" + lruCacheDemo.map.size());

        lruCacheDemo.put(5, 5);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("map大小：" + lruCacheDemo.map.size());
    }
}
