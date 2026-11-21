package juc.pool;

import java.util.concurrent.*;

/**
 * @ClassName ExecutorsDemo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/11 15:26
 **/

// Thread pool utility demonstration.
// Three creation methods, seven constructor parameters, and four rejection policies.
public class ExecutorsDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// Single worker thread
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// Fixed-size thread pool
//        ExecutorService threadPool =Executors.newCachedThreadPool(); // Scalable thread pool
//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            // Maximum capacity = maximumPoolSize + work queue capacity
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    //  AbortPolicy: throws a RejectedExecutionException
    //  CallerRunsPolicy: executes the task on the calling thread
    //  DiscardPolicy: silently discards the rejected task
    //  DiscardOldestPolicy: discards the oldest queued task, then retries submission
}
