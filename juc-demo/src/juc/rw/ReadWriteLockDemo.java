package juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description 读-读 可以共存，读-写 不能共存，写-写 不能共存！
 * @Author ChenYu
 * @Date 2021/8/10 17:50
 **/

// 独占锁（写锁） 一次只能被一个线程所占用
// 共享锁(读锁)  一次可以被多个线程所使用
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        CacheLock cacheLock = new CacheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cacheLock.put(temp + "", temp + "");
            }, i + "").start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cacheLock.get(temp + "");
            }, i + "").start();
        }
    }
}

class CacheLock {
    public static volatile Map<String, String> cache = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String val) {
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "放入键:" + key);
            cache.put(key, val);
            System.out.println(Thread.currentThread().getName() + "放入键OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String get(String key) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "取出键:" + key);
            System.out.println(Thread.currentThread().getName() + "取出键OK");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return cache.get(key);
    }
}
