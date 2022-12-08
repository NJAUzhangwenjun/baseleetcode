package com.wjhub.leetcode.algorithm.strategyMode.forkjoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 斐波那契测试
 *
 * @author zhangwenjun
 * @date 2022/10/19
 */
public class FibonacciTest {

    /**
     * 斐波那契
     *
     * @author zhangwenjun
     * @date 2022/10/19
     */
    class Fibonacci extends RecursiveTask<Integer> {

        /**
         * n
         */
        int n;

        /**
         * 斐波那契
         *
         * @param n n
         */
        public Fibonacci(int n) {
            this.n = n;
        }

        /**
         * 计算
         *
         * @return {@link Integer}
         */// 主要的实现逻辑都在compute()里
        @Override
        protected Integer compute() {
            // 这里先假设 n >= 0
            if (n <= 1) {
                return n;
            } else {
                // f(n-1)
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                // f(n-2)
                Fibonacci f2 = new Fibonacci(n - 2);
                f2.fork();
                // f(n) = f(n-1) + f(n-2)
                return f1.join() + f2.join();
            }
        }
    }

    /**
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     */
    @Test
    public void testFib() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(40);
        Future<Integer> future = forkJoinPool.submit(fibonacci);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d millis", end - start));
    }


}
