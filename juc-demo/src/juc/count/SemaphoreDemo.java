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
        //模拟停车位，一共就只有三个位置
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {//一共有六辆车开进来
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位！");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, i + "").start();
        }
    }
}
