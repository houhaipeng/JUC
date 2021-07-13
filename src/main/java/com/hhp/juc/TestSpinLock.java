package com.hhp.juc;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "t1").start();
        //保证t1先获取到锁
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "t2").start();
    }
}
