package juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description Read-read operations can run concurrently; read-write and write-write operations cannot.
 * @Author ChenYu
 * @Date 2021/8/10 17:50
 **/

// Exclusive lock (write lock): held by only one thread at a time
// Shared lock (read lock): can be held by multiple threads concurrently
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
            System.out.println(Thread.currentThread().getName() + " inserted key:" + key);
            cache.put(key, val);
            System.out.println(Thread.currentThread().getName() + " insertion complete");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String get(String key) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " retrieved key:" + key);
            System.out.println(Thread.currentThread().getName() + " retrieval complete");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return cache.get(key);
    }
}
