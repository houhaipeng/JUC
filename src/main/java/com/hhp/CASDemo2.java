package com.hhp;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //
        //public final boolean compareAndSet(int expect, int update);
        //CAS是CPU的并发原语
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        //自旋锁
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2022, 2021));
        System.out.println(atomicInteger.get());
    }
}
