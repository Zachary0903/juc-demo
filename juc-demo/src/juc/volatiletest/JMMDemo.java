package juc.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMMDemo
 * @Description The three core properties of volatile: 1. ensures thread visibility 2. does not guarantee atomicity 3. prevents instruction reordering
 * @Author ChenYu
 * @Date 2021/8/12 17:53
 **/
public class JMMDemo {
    private volatile static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {
                System.out.println(Thread.currentThread().getName() + "get num" + num);
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
