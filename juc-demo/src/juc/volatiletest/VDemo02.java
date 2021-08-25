package juc.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VDemo02
 * @Description 如何保线程操作的原子性？  在不使用lock/synchronized的情况下 使用atomicXX类。
 * @Author ChenYu
 * @Date 2021/8/12 18:03
 **/
public class VDemo02 {
    private volatile static AtomicInteger num = new AtomicInteger(0);

    public static void add() {
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        //理论上num应该是2W
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(num);
    }
}
