package juc.pool;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

/**
 * @ClassName ExecutorsDemo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/11 15:26
 **/

//线程池工具类。
// 三大方法，七大参数，四个拒绝策略
public class ExecutorsDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
//        ExecutorService threadPool =Executors.newCachedThreadPool(); //可伸缩的，遇强则强，遇弱则弱
//        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            //最大承载数=maximumPoolSize+workqueue的size
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " OK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    //  AbortPolicy : 抛出异常
    //  CallerRunsPolicy: 哪来的回哪个线程去
    //  DiscardPolicy: 队列满了，丢掉任务，不会抛出异常
    //  DiscardOldestPolicy: 队列满了，尝试去和最早的竞争，也不会抛出异常！
}
