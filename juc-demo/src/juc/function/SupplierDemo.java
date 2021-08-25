package juc.function;

import java.util.function.Supplier;

/**
 * @ClassName SupplierDemo
 * @Description 没有参数，只有返回值
 * @Author ChenYu
 * @Date 2021/8/11 19:17
 **/
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = () -> "supplier";
        System.out.println(supplier.get());
    }
}
