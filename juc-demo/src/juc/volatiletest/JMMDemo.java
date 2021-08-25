package juc.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName JMMDemo
 * @Description volatile三大特性：1.保证线程可见性 2.不保证原子性 3.防止指令重排
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
