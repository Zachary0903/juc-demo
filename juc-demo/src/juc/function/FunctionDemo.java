package juc.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @ClassName Demo01
 * @Description Function: returns a result based on the provided input
 * @Author ChenYu
 * @Date 2021/8/11 19:09
 **/
public class FunctionDemo {
    public static void main(String[] args) {
        Function function = o -> o + "1";
        System.out.println(function.apply("test"));
    }
}
