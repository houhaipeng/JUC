package com.hhp;

/**
 * 线程交替执行: A B操作同一个变量num
 * A +1
 * B -1
 * 线程之间的通信问题:生产者和消费者问题!（判断等待，业务， 通知）
 */
public class ProducerAndCustomerDemo {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class Data{//数字 资源类

    private int number = 0;
    //+1
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知,唤醒其他线程
        this.notifyAll();
    }
    //-1
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        //通知
        this.notifyAll();
    }
}
