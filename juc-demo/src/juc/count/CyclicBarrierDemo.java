package juc.count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CyclicBarrierDemo
 * @Description 减法计数器
 * @Author ChenYu
 * @Date 2021/8/10 16:48
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //集齐7颗龙珠召唤神龙
        //召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠，开始召唤神龙！！");
        });
        for (int i = 1; i <= 7; i++) {

            {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "收到了第" + finalI + "颗龙珠！");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}

