package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Demonstrates reentrant locking. lock() and unlock() calls must be balanced; otherwise, the resource may remain locked.
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
