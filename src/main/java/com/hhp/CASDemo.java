package com.hhp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {

    public static void main(String[] args) {

        //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(20, 1);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("a1 =>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(20, 22,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a2 =>" + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(22, 20,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3 =>" + atomicStampedReference.getStamp());
        }, "a").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println("b1 =>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(20, 66, stamp, stamp + 1));

            System.out.println("b2 =>" + atomicStampedReference.getStamp());
        }, "b").start();
    }

}
