package com.hhp;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 *
 * 如何使用forkJson
 * 1. forkJoinPool 通过它来执行
 * 2. 计算任务 forkjoinPool.execute(ForkJoinTask<?> task)
 * 3. 计算类要继承
 */
public class ForkJoin extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    //临界值
    private Long temp = 10000L;

    public ForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {//forkjoin
            long middle = (start + end) / 2;//中间值
            ForkJoin task1 = new ForkJoin(start, middle);
            task1.fork();//拆分任务，把任务压入线程队列
            ForkJoin task2 = new ForkJoin(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
