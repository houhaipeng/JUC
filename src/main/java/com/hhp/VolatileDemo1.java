package com.hhp;

import java.util.concurrent.TimeUnit;

/**
 * volatile的可见性验证
 */
public class VolatileDemo1 {

    private volatile static int num = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (num == 0) {

            }
        },"T").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程对共享变量进行修改，如果不加volatile关键字，则T线程对num不可见，T线程会在while死循环
        num = 1;
        System.out.println(num);
    }
}
