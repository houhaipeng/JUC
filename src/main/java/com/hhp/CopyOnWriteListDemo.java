package com.hhp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发下ArrayList不安全，java.util.ConcurrentModificationException 并发修改异常
 * 解决方案：
 *  1. List<String> list = new Vector<>();
 *  2. List<String> list = Collections.synchronizedList(new ArrayList<>();
 *  3. List<String> list = new CopyOnWriteArrayList<>();
 */
public class CopyOnWriteListDemo {

    public static void main(String[] args) {
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
        //CopyOnWriteArrayList的add用的是ReentrantLock锁
        //Vector的add用的是synchronized
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {

            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }


}
