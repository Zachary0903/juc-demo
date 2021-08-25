package juc.lock;

//可重入锁测试，代表获得了外面的一把也就获得了房间内的锁；好比你有大门的锁就一定会有卧室的锁
public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        new Thread(() -> {
            demo.sms();
        }, "A").start();

        new Thread(() -> {
            demo.sms();
        }, "B").start();
    }

    private synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "sms()");
        call();
    }

    private synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "call()");
    }
}
