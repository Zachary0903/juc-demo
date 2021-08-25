package juc.function;

import java.util.function.Predicate;

/**
 * @ClassName Demo02
 * @Description Predicate: 判断字符串是否为空
 * @Author ChenYu
 * @Date 2021/8/11 19:14
 **/
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("233"));
    }
}
