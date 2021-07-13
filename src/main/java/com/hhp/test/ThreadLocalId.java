package com.hhp.test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }

    public static void remove() {
        threadId.remove();
    }

    private static void incrementSameThreadId() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "_" + i + "-->threadId:" + ThreadLocalId.get());
            }
        } finally {
            ThreadLocalId.remove();
        }
    }

    public static void main(String[] args) {
        incrementSameThreadId();
        new Thread(()->{incrementSameThreadId();}).start();
        new Thread(()->{incrementSameThreadId();}).start();
    }
}
