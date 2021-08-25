package juc.future;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo01
 * @Description 异步调用：CompletableFuture
 * @Author ChenYu
 * @Date 2021/8/11 21:31
 **/
public class Demo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求
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


        //有返回值的supplyAsync 异步回调

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync()");
            int i = 10 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println(Thread.currentThread().getName() + "whenComplete()");
            System.out.println(t);//成功结果
            System.out.println(u);//失败结果
        }).exceptionally(e -> {
            return 500;
        }).get());

//        System.out.println(completableFuture.get());
    }
}
