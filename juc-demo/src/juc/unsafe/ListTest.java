package juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName ListTest
 * @Description 集合测试
 * @Author ChenYu
 * @Date 2021/7/13 21:27
 **/
public class ListTest {
    // java.util.ConcurrentMoidificatioException 集合类并发修改异常
    // 有几种解决方案：

    /**
     * 1.用vector
     * 2.用Collectors.synchronized()包装下
     * 3.使用CopyOnWriteArrayList、CopyOnWriteArraySet、ConcurrentHashMap
     */

    //CopyOnWrite 写入时赋值 COW 计算机程序设计领域时的一种优化策略
    // CopyOnWriteArrayList和vector的区别，vector底层使用synchorinized效率比较低，CopyOnWriteArrayList使用reentrantLock
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
