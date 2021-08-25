package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//可重入锁测试，ReentrantLock本身就是可重入锁的意思。要注意的是Lock和unlock必须一一配对，否则会死锁！！
public class ReentrantLockDemo {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        new Thread(() -> {
            demo.sms();
        }, "A").start();

        new Thread(() -> {
            demo.sms();
        }, "B").start();
    }

    private void sms() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "sms()");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "call()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
}
