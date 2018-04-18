package com.lxk.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    //线程池方式
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5); //无需及时处理，任务量会很大，控制线程
}
