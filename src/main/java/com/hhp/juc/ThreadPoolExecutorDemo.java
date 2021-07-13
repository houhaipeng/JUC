package com.hhp.juc;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //只用调用者所在线程(此处为main线程)来运行任务
//                new ThreadPoolExecutor.CallerRunsPolicy()
                //直接抛出异常
//                new ThreadPoolExecutor.AbortPolicy()
                //不处理，丢弃掉
//                new ThreadPoolExecutor.DiscardPolicy()
                //丢弃队列中最近的一个任务，并执行当前任务
                new ThreadPoolExecutor.DiscardOldestPolicy()
    );

        try {
            //最大承载：Queue + max
            for (int i = 0; i < 9; i++) {
                //向线程池提交任务，100个任务
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }
    }
}
