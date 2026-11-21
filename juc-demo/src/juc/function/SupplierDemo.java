package juc.function;

import java.util.function.Supplier;

/**
 * @ClassName SupplierDemo
 * @Description Accepts no parameters and returns a result
 * @Author ChenYu
 * @Date 2021/8/11 19:17
 **/
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = () -> "supplier";
        System.out.println(supplier.get());
    }
}
