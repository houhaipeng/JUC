package com.hhp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile的原子性验证
 * volatile不保证原子性,如果不加lock或synchronized，怎样保证原子性?
 */
public class VolatileDemo2 {

    //原子类
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {

        //理论上结果为20000
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        //当所有线程跑完，则main线程输出
        while (Thread.activeCount() > 2) {//main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() +" "+ num);
    }

    public static void add() {
//        num++;//不是一个原子性操作
        num.getAndIncrement();//+1 CAS
    }
}
