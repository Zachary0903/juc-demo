package juc.lockeight;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test1
 * @Description Eight-lock test
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

    // synchronized locks the method receiver; a static synchronized method locks the class template
    // Both methods use the same lock; execution order is determined by lock acquisition
    public synchronized void sendSms() {
        System.out.println("Send SMS");
    }

    public synchronized void call() {
        System.out.println("Make a phone call");

    }
}