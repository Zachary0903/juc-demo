package juc.function;

import java.util.function.Consumer;

/**
 * @ClassName ConsumerDemo
 * @Description 只有输入，没有返回值
 * @Author ChenYu
 * @Date 2021/8/11 19:17
 **/
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("aa");
    }
}
