package juc.lock;

// Demonstrates reentrant synchronization: acquiring the outer lock also grants access to nested synchronized blocks.
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
