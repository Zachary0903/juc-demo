package juc;

/**
 * @ClassName ConditionTest
 * @Description 同步监视器测试类
 * @Author ChenYu
 * @Date 2021/7/13 20:36
 **/
public class ConditionTest {
    public static void main(String[] args) {
        ProcessData data = new ProcessData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();

            }
        }
                , "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();

            }
        }, "C").start();
    }
}
