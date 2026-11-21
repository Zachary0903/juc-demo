package juc.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VDemo02
 * @Description How can thread operations remain atomic without lock or synchronized? Use atomic classes.
 * @Author ChenYu
 * @Date 2021/8/12 18:03
 **/
public class VDemo02 {
    private volatile static AtomicInteger num = new AtomicInteger(0);

    public static void add() {
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        // The result should theoretically be 20,000
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
