package com.hhp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A执行完调用B,B执行完调用C,C执行完调用A
 */
public class ProducerAndCustomerDemo3 {
    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

class Data3{//数字 资源类

   private Lock lock = new ReentrantLock();
   private Condition condition1 = lock.newCondition();
   private Condition condition2 = lock.newCondition();
   private Condition condition3 = lock.newCondition();

   private int number = 1;

   public void printA() {
       lock.lock();
       //业务
       try {
           while (number != 1) {
               //
               condition1.await();
           }
           System.out.println(Thread.currentThread().getName() + "AAAAAAA");
           //唤醒
           number = 2;
           condition2.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
   }
   public void printB() {
       lock.lock();
       //业务
       try {
           while (number != 2) {
               //等待
               condition2.await();
           }
           System.out.println(Thread.currentThread().getName() + "BBBBBBBB");
           //唤醒
           number = 3;
           condition3.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
   }
   public void printC() {
       lock.lock();
       //业务
       try {
           while (number != 3) {
               //
               condition3.await();
           }
           System.out.println(Thread.currentThread().getName() + "CCCCCCCCC");
           //唤醒
           number = 1;
           condition1.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
   }
}
