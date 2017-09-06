package com.joe.phonebook.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池，线程管理辅助类（管理整个应用的所有线程）
 * @author joe
 * Created by Think on 2017/1/17.
 */

public class ThreadPoolUtils {

    public ThreadPoolUtils() {
        super();
    }
    /**线程池核心数*/
    private static int CORE_POOL_SIZE = 5;
    /**线程池最大线程数*/
    private static int MAX_POOL_SIZE = 100;
    /**额外线程空状态存活时间 */
    private static int KEEP_ALIVE_TIME = 10000;
    /**阻塞队列 当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程*/
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
    /**线程池*/
    private static ThreadPoolExecutor threadPoolExecutor;
    /**线程工厂*/
    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger atomicInteger = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            //获取当前的值，并自增
            return new Thread(r,"threadPool thread:"+ atomicInteger.getAndIncrement());
        }
    };
    /**静态代码块*/
    static {
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
    }
    /**从线程池中抽取线程，执行指定的Runnable对象*/
    public static void execute(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }




}
