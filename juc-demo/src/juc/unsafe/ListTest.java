package juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ListTest
 * @Description Collection concurrency test
 * @Author ChenYu
 * @Date 2021/7/13 21:27
 **/
public class ListTest {
    // java.util.ConcurrentModificationException indicates concurrent collection modification.
    // Common solutions:

    /**
     * 1. Use Vector
     * 2. Wrap the collection with Collections.synchronized* methods
     * 3. Use CopyOnWriteArrayList, CopyOnWriteArraySet, or ConcurrentHashMap
     */

    // Copy-on-write (COW) is an optimization strategy in which writes operate on a copy.
    // CopyOnWriteArrayList uses ReentrantLock, while Vector uses synchronized and generally has lower concurrency throughput.
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }).start();

        }
    }
}
