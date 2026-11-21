package juc.lock;

import java.util.concurrent.TimeUnit;

// This scenario creates a deadlock. Diagnose it with:
// 1. Use jps -l to locate the process ID.
// 2. Use jstack with the process ID to inspect the thread dump (for example, "Found 1 deadlock").
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThread(lockA, lockB)).start();
        new Thread(new MyThread(lockB, lockA)).start();

    }

    static class MyThread extends Thread {
        private String lockA;
        private String lockB;

        MyThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA.intern()) {
                System.out.println(Thread.currentThread().getName() + "lockA==>To getB()");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB.intern()) {
                    System.out.println(Thread.currentThread().getName() + "lockB==>To getA()");
                }
            }
        }
    }
}
