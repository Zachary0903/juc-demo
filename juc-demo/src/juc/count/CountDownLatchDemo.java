package juc.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CountDownLatch
 * @Description Demonstrates a count-down latch
 * @Author ChenYu
 * @Date 2021/8/10 16:42
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // The initial count is 6; use the latch when tasks must finish before proceeding.
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
