package com.hhp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  java.util.ConcurrentModificationException 并发修改异常
 *
 *  解决方案：
 *   1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
 *   2.
 */
public class CopyOnWriteArraySetDemo {

    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();

        Set<String> set = Collections.synchronizedSet(new HashSet<>());

//        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 10; i++) {

            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
