package juc.lockeight;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description 八锁测试
 * @Author ChenYu
 * @Date 2021/7/13 20:54
 **/
public class Test1 {
    public static void main(String[] args) throws Exception {
        MobilePhone mobilePhone = new MobilePhone();
        new Thread(() -> {
            mobilePhone.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            mobilePhone.call();
        }).start();
    }


}


class MobilePhone {

    //synchronized锁的对象是方法的调用者，如果synchronized前面有static静态方法的话锁的是class模板
    //两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendSms() {
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");

    }
}