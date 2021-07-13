package com.hhp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交替执行: A B C D操作同一个变量num
 * A +1
 * B -1
 * C +1
 * D -1
 * 顺序执行
 * 线程之间的通信问题:生产者和消费者问题!（判断等待，业务， 通知）
 */
public class ProducerAndCustomerDemo2 {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"D").start();
    }
}

class Data2{//数字 资源类

    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //+1
    public void increment() {

        lock.lock();
        try {
            while (number != 0) {
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知,唤醒其他线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //-1
    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                //等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
