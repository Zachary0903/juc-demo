package juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo01
 * @Description Asynchronous invocation with CompletableFuture
 * @Author ChenYu
 * @Date 2021/8/11 21:31
 **/
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Initiate a request
//        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync()");
//        });

//        System.out.println("main");
//        completableFuture.get();


        // supplyAsync performs an asynchronous callback and returns a result

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync()");
            int i = 10 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println(Thread.currentThread().getName() + "whenComplete()");
            System.out.println(t);// Successful result
            System.out.println(u);// Failed result
        }).exceptionally(e -> {
            return 500;
        }).get());

//        System.out.println(completableFuture.get());
    }
}
