package juc;

/**
 * @ClassName LockTest
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/7/13 19:57
 **/
public class LockTest {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();

            }
        }
                , "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();

            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();

            }
        }, "D").start();
    }
}
