package juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableTest
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/10 16:25
 **/
public class CallableTest {
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();//结果会被缓存

        System.out.println(futureTask.get());//结果可能需要等待，会阻塞
    }
}


class MyCallable implements Callable<String> {

    @Override
    public String call() {
        System.out.println("call==>>");
        return "OK";
    }
}