package com.anyu.armmodule.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @creator: Created by LiYuLong
 * @time: on 2018/3/6
 * @Describe: 线程池
 */
public class Pool {
    private final static int POOL_SIZE = 4;// 线程池的大小最好设置成为CUP核数的2N
    private final static int MAX_POOL_SIZE = 6;// 设置线程池的最大线程数
    private final static int KEEP_ALIVE_TIME = 4;// 设置线程的存活时间
    private Executor mExecutor;
    private static Pool pool;

    public Pool() {
        DoInit();
    }

    private void DoInit() {
        //工厂 as handler
        //ThreadFactory factory = new MyThreadFactory("ThreadPool", Process.THREAD_PRIORITY_BACKGROUND);
        //工作队列 as MessageQueue
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        mExecutor = new ThreadPoolExecutor(
                POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                workQueue
        );
    }

    public static Pool getPool() {
        return pool == null ? new Pool() : pool;
    }

    public void submit(Runnable command) {
        //执行
        mExecutor.execute(command);
    }
}

