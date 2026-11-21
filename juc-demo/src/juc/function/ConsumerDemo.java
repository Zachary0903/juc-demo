package juc.function;

import java.util.function.Consumer;

/**
 * @ClassName ConsumerDemo
 * @Description Accepts an input and returns no result
 * @Author ChenYu
 * @Date 2021/8/11 19:17
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("aa");
    }
}
