package juc.count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CyclicBarrierDemo
 * @Description Demonstrates a cyclic barrier
 * @Author ChenYu
 * @Date 2021/8/10 16:48
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // Gather seven Dragon Balls to summon Shenron
        // Thread that summons Shenron
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("All seven Dragon Balls collected. Summoning Shenron!");
        });
        for (int i = 1; i <= 7; i++) {

            {
                int finalI = i;
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + " received Dragon Ball #" + finalI);
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
