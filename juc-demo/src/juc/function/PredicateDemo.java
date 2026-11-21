package juc.function;

import java.util.function.Predicate;

/**
 * @ClassName Demo02
 * @Description Predicate: determines whether a string is empty
 * @Author ChenYu
 * @Date 2021/8/11 19:14
 **/
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("233"));
    }
}
