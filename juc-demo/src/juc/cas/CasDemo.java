package juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

//解决CAS自旋引起的ABA问题，
public class CasDemo {

    //注意，如果AtomicStampedReference的泛型是一个包装类，注意对象的引用问题。
    //Integer超过-127~128，会产生新的对象，这时候比较和原对象相等即会失败。这是一个大坑。
    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.print("A==>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(stampedReference.compareAndSet(1, 2,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));
            System.out.print("A==>" + stampedReference.getStamp());
            System.out.println(stampedReference.compareAndSet(2, 1,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));
            System.out.print("A==>" + stampedReference.getStamp());

        }, "A").start();


        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(stampedReference.compareAndSet(1, 66,
                    stamp, stampedReference.getStamp() + 1));
        }, "B").start();
    }
}
