package juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;


/**
 * @ClassName Demo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/11 20:10
 **/
public class Demo {
    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
    }


    // Baseline implementation using a sequential loop
    private static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        //add here
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", execution time=" + (end - start) + " ms");
    }

    // ForkJoin implementation: sum=500000000500000000, execution time=5790 ms
    private static void test2() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        java.util.concurrent.ForkJoinTask<Long> result = forkJoinPool.submit(new ForkJoinTask(0L, 10_0000_0000L));
        try {
            sum = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //add here
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", execution time=" + (end - start) + " ms");
    }

    private static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ", execution time=" + (end - start) + " ms");
    }
}
