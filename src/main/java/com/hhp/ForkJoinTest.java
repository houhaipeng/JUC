package com.hhp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        //sum=499999999500000000时间：5260
        System.out.println("sum=" + sum + "时间：" +(end - start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoin(0L, 10_0000_0000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);//提交任务
        Long sum = result.get();
        long end = System.currentTimeMillis();
        //sum=499999999500000000时间：2804
        System.out.println("sum=" + sum + "时间：" +(end - start));
    }
    public static void test3() {
        long start = System.currentTimeMillis();
        //stream并行流,
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        //sum=500000000500000000时间：205
        System.out.println("sum=" + sum + "时间：" +(end - start));
    }
}
