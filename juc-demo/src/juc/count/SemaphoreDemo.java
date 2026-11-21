package juc.count;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreDemo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/10 17:19
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        // Simulate three parking spaces
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {// Six vehicles attempt to enter
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " acquired a parking space!");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " released a parking space!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, i + "").start();
        }
    }
}
