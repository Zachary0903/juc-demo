package juc.unsafe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName ListTest
 * @Description 集合测试
 * @Author ChenYu
 * @Date 2021/7/13 21:27
 **/
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }).start();
        }
    }
}
