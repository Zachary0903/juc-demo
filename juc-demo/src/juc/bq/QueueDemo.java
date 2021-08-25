package juc.bq;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName QueueDemo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/10 18:17
 **/
public class QueueDemo {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * @param
     * @return void
     * @Description: 会抛出异常的, 使用add/remove
     */
    public static void test1() {
        List<String> list = Arrays.asList("a", "b", "c");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3, false, list);
        System.out.println("队首是:" + blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println("队首是:" + blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println("队首是:" + blockingQueue.element());

//        System.out.println(blockingQueue.remove());

    }

    /**
     * @param
     * @return void
     * @Description: 不会抛出异常的, 使用offer/poll
     */
    public static void test2() {
        List<String> list = Arrays.asList("a", "b", "c");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3, false, list);
        System.out.println("队首是:" + blockingQueue.peek());

//        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println("队首是:" + blockingQueue.peek());

    }

    /**
     * @param
     * @return void
     * @Description: 等待，阻塞（一直阻塞）,使用Put/take
     */
    public static void test3() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        try {
            blockingQueue.put("1");
            blockingQueue.put("2");
            blockingQueue.put("3");
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("队首是:"+blockingQueue.peek());

    }

    /**
     * @param
     * @return void
     * @Description: 等待，阻塞（等待超时，超过时间就不等了）
     */
    public static void test4() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        try {
            blockingQueue.offer("d", 2, TimeUnit.SECONDS);
            System.out.println("===========>");
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    //同步队列test,同步队列和其他的blockingQueue不一样，
    // synchronousQueue不存储元素，put了一个元素，必须从里面take出来，否则不能再put进去。
    public static void test5() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "==>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "==>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "==>" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
