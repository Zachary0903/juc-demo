package juc.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName ListTest
 * @Description 集合测试
 * @Author ChenYu
 * @Date 2021/7/13 21:27
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
