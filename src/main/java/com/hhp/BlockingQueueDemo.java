package com.hhp;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

//        test1();
        test2();
    }

    /**
     * 抛出异常
     */
    public static void test1() {

        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");

        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，没有抛出异常
     */
    public static void test2() {

        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.poll());
    }
}
