package juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

// Resolve the ABA problem caused by CAS spin loops.
public class CasDemo {

    // If AtomicStampedReference uses a wrapper type, account for object reference semantics.
    // Integer values outside -128 to 127 create new objects, so reference-based comparison may unexpectedly fail.
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
