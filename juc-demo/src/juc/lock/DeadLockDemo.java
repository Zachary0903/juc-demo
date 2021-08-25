package juc.lock;

import java.util.concurrent.TimeUnit;

//这种情况就陷入死锁，解决方法：
//1.使用Jps 定位我们的进程号 jps -l
// 2.  jstack 加上1获取的进程号 发现提示 Found 1 deadLOCK.
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
