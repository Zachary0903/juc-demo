package juc.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CountDownLatch
 * @Description 加法计数器
 * @Author ChenYu
 * @Date 2021/8/10 16:42
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须要执行任务的时候，再使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " has left");
                countDownLatch.countDown();
            }, i + "").start();
        }
        countDownLatch.await();
        System.out.println("Able to close the door!");
    }
}
