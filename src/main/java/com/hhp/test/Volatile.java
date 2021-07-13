package com.hhp.test;

import java.util.concurrent.TimeUnit;

public class Volatile {
    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (num == 0) {}
        }, "T").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
