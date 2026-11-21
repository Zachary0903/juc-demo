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
     * @Description: Operations that throw exceptions: add/remove
     */
    public static void test1() {
        List<String> list = Arrays.asList("a", "b", "c");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3, false, list);
        System.out.println("Queue head:" + blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println("Queue head:" + blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println("Queue head:" + blockingQueue.element());

//        System.out.println(blockingQueue.remove());

    }

    /**
     * @param
     * @return void
     * @Description: Operations that do not throw exceptions: offer/poll
     */
    public static void test2() {
        List<String> list = Arrays.asList("a", "b", "c");
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3, false, list);
        System.out.println("Queue head:" + blockingQueue.peek());

//        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println("Queue head:" + blockingQueue.peek());

    }

    /**
     * @param
     * @return void
     * @Description: Blocking operations that wait indefinitely: put/take
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
//        System.out.println("Queue head:" + blockingQueue.peek());

    }

    /**
     * @param
     * @return void
     * @Description: Timed blocking operations that stop waiting after a timeout
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

    // SynchronousQueue test. Unlike other BlockingQueue implementations,
    // SynchronousQueue does not store elements. A put call must be matched by a take call.
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
